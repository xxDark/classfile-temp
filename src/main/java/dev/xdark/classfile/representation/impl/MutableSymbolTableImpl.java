package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.attribute.klass.BootstrapMethod;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.ConstantClass;
import dev.xdark.classfile.constantpool.ConstantDynamicAlike;
import dev.xdark.classfile.constantpool.ConstantFieldRef;
import dev.xdark.classfile.constantpool.ConstantInterfaceMethodRef;
import dev.xdark.classfile.constantpool.ConstantInvokeDynamic;
import dev.xdark.classfile.constantpool.ConstantMethodHandle;
import dev.xdark.classfile.constantpool.ConstantMethodRef;
import dev.xdark.classfile.constantpool.ConstantMethodType;
import dev.xdark.classfile.constantpool.ConstantNameAndType;
import dev.xdark.classfile.constantpool.ConstantString;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.constant.impl.ConstantInternal;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.DynamicAlike;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.ReferenceKind;
import dev.xdark.classfile.type.Type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MutableSymbolTableImpl extends SymbolTableImpl implements MutableSymbolTable {
	private final Map<BMKey, Integer> bootstrapMethodPool;

	public MutableSymbolTableImpl(MutableConstantPool constantPool, BootstrapMethodsAttribute bootstrapMethodsAttribute) {
		super(constantPool, bootstrapMethodsAttribute);
		Map<BMKey, Integer> bootstrapMethodPool = new HashMap<>();
		List<BootstrapMethod> bootstrapMethods = bootstrapMethodsAttribute.bootstrapMethods();
		for (int i = 0; i < bootstrapMethods.size(); i++) {
			BootstrapMethod bootstrapMethod = bootstrapMethods.get(i);
			bootstrapMethodPool.putIfAbsent(new BMKey(bootstrapMethod), i);
		}
		this.bootstrapMethodPool = bootstrapMethodPool;
	}

	@Override
	public MutableConstantPool constantPool() {
		return (MutableConstantPool) super.constantPool();
	}

	@Override
	public int addString(String value) {
		MutableConstantPool constantPool = (MutableConstantPool) this.constantPool;
		return constantPool.add(ConstantString.create(constantPool.add(ConstantUtf8.create(value))));
	}

	@Override
	public int addType(Type type) {
		MutableConstantPool constantPool = (MutableConstantPool) this.constantPool;
		if (type instanceof ObjectType) {
			return constantPool.add(ConstantClass.create(constantPool.add(ConstantUtf8.create(((ObjectType) type).internalName()))));
		}
		return constantPool.add(ConstantMethodType.create(constantPool.add(ConstantUtf8.create(((MethodType) type).descriptor()))));
	}

	@Override
	public int addMethodHandle(MethodHandle methodHandle) {
		MutableConstantPool constantPool = (MutableConstantPool) this.constantPool;
		ReferenceKind kind = methodHandle.kind();
		int owner = addType(methodHandle.owner());
		int nameAndType = constantPool.add(ConstantNameAndType.create(
				constantPool.add(ConstantUtf8.create(methodHandle.name())),
				constantPool.add(ConstantUtf8.create(methodHandle.type().descriptor()))
		));
		boolean itf = methodHandle.isInterface();
		ConstantMemberRefHelper memberRefHelper = new ConstantMemberRefHelper(owner, nameAndType);
		int bootstrapRef;
		switch (kind) {
			case REF_getField:
			case REF_getStatic:
			case REF_putField:
			case REF_putStatic:
				bootstrapRef = memberRefHelper.asFieldRef(constantPool);
				break;
			case REF_invokeVirtual:
			case REF_newInvokeSpecial:
				bootstrapRef = memberRefHelper.asMethodRef(constantPool);
				break;
			case REF_invokeStatic:
			case REF_invokeSpecial:
				bootstrapRef = itf ? memberRefHelper.asInterfaceMethodRef(constantPool) : memberRefHelper.asMethodRef(constantPool);
				break;
			case REF_invokeInterface:
				bootstrapRef = memberRefHelper.asInterfaceMethodRef(constantPool);
				break;
			default:
				throw new IllegalStateException("Illegal kind " + kind);
		}
		return constantPool.add(ConstantMethodHandle.create(
				kind,
				bootstrapRef
		));
	}

	@Override
	public int addInvokeDynamic(InvokeDynamic invokeDynamic) {
		return addDynamic(Tag.InvokeDynamic, invokeDynamic);
	}

	@Override
	public int addConstantDynamic(ConstantDynamic constantDynamic) {
		return addDynamic(Tag.ConstantDynamic, constantDynamic);
	}

	@Override
	public BootstrapMethodsAttribute createBootstrapMethods() {
		return bootstrapMethodsAttribute;
	}

	private int addDynamic(Tag<? extends ConstantDynamicAlike> tag, DynamicAlike cst) {
		MutableConstantPool constantPool = (MutableConstantPool) this.constantPool;
		int bsm = addMethodHandle(cst.bootstrapMethodHandle());
		int nameAndType = constantPool.add(ConstantNameAndType.create(
				constantPool.add(ConstantUtf8.create(cst.name())),
				constantPool.add(ConstantUtf8.create(cst.type().descriptor()))
		));
		List<LoadableConstant> args = cst.arguments();
		int[] indices = new int[args.size()];
		int i = 0;
		for (LoadableConstant arg : args) {
			indices[i++] = ((ConstantInternal) arg).store(this);
		}
		BMKey key = new BMKey(bsm, indices);
		List<BootstrapMethod> methods = bootstrapMethodsAttribute.bootstrapMethods();
		int bootstrapIndex;
		{
			bootstrapIndex = methods.size();
			Integer prev = bootstrapMethodPool.putIfAbsent(key, bootstrapIndex);
			if (prev == null) {
				methods.add(BootstrapMethod.create(bsm, indices));
			} else {
				bootstrapIndex = prev;
			}
		}
		if (tag == Tag.InvokeDynamic) {
			return constantPool.add(ConstantInvokeDynamic.create(bootstrapIndex, nameAndType));
		}
		return constantPool.add(dev.xdark.classfile.constantpool.ConstantDynamic.create(bootstrapIndex, nameAndType));
	}

	private static final class BMKey {
		final int referenceIndex;
		final int[] argumentIndices;

		BMKey(int referenceIndex, int[] argumentIndices) {
			this.referenceIndex = referenceIndex;
			this.argumentIndices = argumentIndices;
		}

		BMKey(BootstrapMethod method) {
			this(method.referenceIndex(), method.argumentIndices());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			BMKey BMKey = (BMKey) o;

			if (referenceIndex != BMKey.referenceIndex) return false;
			return Arrays.equals(argumentIndices, BMKey.argumentIndices);
		}

		@Override
		public int hashCode() {
			int result = referenceIndex;
			result = 31 * result + Arrays.hashCode(argumentIndices);
			return result;
		}
	}

	private static final class ConstantMemberRefHelper {
		final int classIndex;
		final int nameAndTypeIndex;

		ConstantMemberRefHelper(int classIndex, int nameAndTypeIndex) {
			this.classIndex = classIndex;
			this.nameAndTypeIndex = nameAndTypeIndex;
		}

		int asFieldRef(MutableConstantPool constantPool) {
			return constantPool.add(ConstantFieldRef.create(classIndex, nameAndTypeIndex));
		}

		int asMethodRef(MutableConstantPool constantPool) {
			return constantPool.add(ConstantMethodRef.create(classIndex, nameAndTypeIndex));
		}

		int asInterfaceMethodRef(MutableConstantPool constantPool) {
			return constantPool.add(ConstantInterfaceMethodRef.create(classIndex, nameAndTypeIndex));
		}
	}
}
