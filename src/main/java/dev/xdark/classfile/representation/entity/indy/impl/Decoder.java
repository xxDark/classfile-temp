package dev.xdark.classfile.representation.entity.indy.impl;

import dev.xdark.classfile.attribute.klass.BootstrapMethod;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.ConstantDynamicAlike;
import dev.xdark.classfile.constantpool.ConstantInterfaceMethodRef;
import dev.xdark.classfile.constantpool.ConstantMemberRef;
import dev.xdark.classfile.constantpool.ConstantMethodHandle;
import dev.xdark.classfile.constantpool.ConstantNameAndType;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.DynamicAlike;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.impl.SymbolTableHelper;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.ReferenceKind;
import dev.xdark.classfile.type.Type;
import dev.xdark.classfile.type.TypeIterator;

import java.util.ArrayList;
import java.util.List;

public final class Decoder {
	private final SymbolTable symbolTable;
	private DynamicAlike[] dynamicCache;

	public Decoder(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public static MethodHandle decodeMethodHandle(ConstantPool constantPool, ConstantMethodHandle cst) {
		ReferenceKind referenceKind = cst.referenceKind();
		ConstantMemberRef memberRef = (ConstantMemberRef) constantPool.get(cst.referenceIndex());
		ConstantNameAndType nameAndType = constantPool.get(memberRef.nameAndTypeIndex(), Tag.NameAndType);
		return new MethodHandleImpl(
				referenceKind,
				ObjectType.ofInternalName(constantPool.get(constantPool.get(memberRef.classIndex(), Tag.Class).nameIndex(), Tag.Utf8).value()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				singleType(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value()),
				memberRef instanceof ConstantInterfaceMethodRef
		);
	}

	public ConstantDynamic decodeConstantDynamic(int index, ConstantDynamicAlike cst, BootstrapMethodsAttribute attribute) {
		return decodeDynamic(index, cst, attribute, ConstantDynamic::create);
	}

	public InvokeDynamic decodeInvokeDynamic(int index, ConstantDynamicAlike cst, BootstrapMethodsAttribute attribute) {
		return decodeDynamic(index, cst, attribute, InvokeDynamic::create);
	}

	private <C extends DynamicAlike> C decodeDynamic(int cache, ConstantDynamicAlike cst, BootstrapMethodsAttribute attribute, DynamicCreator<C> creator) {
		DynamicAlike[] arr;
		decode:
		{
			if (cache != -1) {
				arr = dynamicCache;
				DynamicAlike c;
				if (arr != null && (c = arr[cache]) != null)
					return (C) c;
				break decode;
			}
			arr = null;
		}
		SymbolTable symtab = this.symbolTable;
		ConstantPool constantPool = symtab.constantPool();
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		String name = constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value();
		String descriptor = constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value();
		Type type = singleType(descriptor);
		BootstrapMethod bootstrapMethod = attribute.bootstrapMethods().get(cst.bootstrapMethodAttributeIndex());
		ConstantMethodHandle cmh = constantPool.get(bootstrapMethod.referenceIndex(), Tag.MethodHandle);
		MethodHandle mh = decodeMethodHandle(constantPool, cmh);
		List<LoadableConstant> arguments = new ArrayList<>();
		C dynamic = creator.create(name, type, mh, arguments);
		if (cache != -1) {
			if (arr == null) {
				arr = new DynamicAlike[constantPool.size()];
				dynamicCache = arr;
			}
			arr[cache] = dynamic;
		}
		for (int argument : bootstrapMethod.argumentIndices()) {
			arguments.add(decodeBootstrapArgument(argument));
		}
		return dynamic;
	}

	private LoadableConstant decodeBootstrapArgument(int index) {
		return SymbolTableHelper.asLoadable(symbolTable, index);
	}

	private static <T extends Type> T singleType(String str) {
		TypeIterator iterator = new TypeIterator(str);
		Type type = iterator.next();
		if (iterator.hasNext()) {
			throw new IllegalStateException("Invalid descriptor " + str);
		}
		return (T) type;
	}

	public interface DynamicCreator<C extends DynamicAlike> {

		C create(String name, Type type, MethodHandle methodHandle, List<LoadableConstant> arguments);
	}
}
