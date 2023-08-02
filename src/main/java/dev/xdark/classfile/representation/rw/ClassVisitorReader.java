package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.ClassVisitor;
import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.MethodVisitor;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.BasicConstantPool;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ClassVisitorReader implements ClassVisitor {
	final MutableConstantPool constantPool = new BasicConstantPool();
	dev.xdark.classfile.representation.ClassVisitor cv;
	BootstrapMethodsAttribute bootstrapMethodsAttribute;
	SymbolTable symbolTable;

	public ClassVisitorReader(dev.xdark.classfile.representation.ClassVisitor cv) {
		this.cv = cv;
	}

	@Override
	public void visitVersion(ClassFileVersion version) {
		cv.visitVersion(version);
	}

	@Override
	public MutableConstantPool visitConstantPool() {
		return constantPool;
	}

	@Override
	public void visitHeader(int accessFlags, int thisClass, int superClass, int[] interfaces) {
		MutableConstantPool constantPool = this.constantPool;
		SymbolTable symtab = SymbolTable.wrap(constantPool, bootstrapMethodsAttribute);
		cv.visitSymbolTable(symtab);
		symbolTable = symtab;
		cv.visitHeader(
				accessFlags,
				getInstanceType(constantPool, thisClass),
				getInstanceType(constantPool, superClass),
				IntStream.of(interfaces).mapToObj(x -> getInstanceType(constantPool, x)).collect(Collectors.toList())
		);
	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, int nameIndex, int descriptorIndex) {
		ConstantPool constantPool = this.constantPool;
		dev.xdark.classfile.representation.MethodVisitor mv = cv.visitMethod(
				accessFlags,
				constantPool.get(nameIndex, Tag.Utf8).value(),
				MethodType.ofDescriptor(constantPool.get(descriptorIndex, Tag.Utf8).value())
		);
		return mv == null ? null : new MethodVisitorReader(mv, symbolTable);
	}

	@Override
	public FieldVisitor visitField(int accessFlags, int nameIndex, int descriptorIndex) {
		ConstantPool constantPool = this.constantPool;
		dev.xdark.classfile.representation.FieldVisitor fv = cv.visitField(
				accessFlags,
				constantPool.get(nameIndex, Tag.Utf8).value(),
				ClassType.ofDescriptor(constantPool.get(descriptorIndex, Tag.Utf8).value())
		);
		return fv == null ? null : new FieldVisitorReader(fv, symbolTable);
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return new ClassAttributeCollector(this);
	}

	private static InstanceType getInstanceType(ConstantPool constantPool, int idx) {
		if (idx == 0) return null;
		return InstanceType.ofInternalName(
				constantPool.get(constantPool.get(idx, Tag.Class).nameIndex(), Tag.Utf8).value()
		);
	}
}
