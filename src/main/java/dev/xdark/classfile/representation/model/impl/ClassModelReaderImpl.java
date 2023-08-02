package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.MethodVisitor;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.constantpool.BasicConstantPool;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.model.ClassModel;
import dev.xdark.classfile.representation.model.ClassModelReader;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.type.InstanceType;

import java.util.ArrayList;
import java.util.List;

public final class ClassModelReaderImpl implements ClassModelReader {
	private final List<FieldVisitorImpl> fields = new ArrayList<>();
	private final List<MethodVisitorImpl> methods = new ArrayList<>();
	private ClassFileVersion version;
	private MutableSymbolTable symbolTable;
	private int accessFlags;
	private InstanceType type;
	private InstanceType superType;
	private List<InstanceType> interfaces;

	@Override
	public void visitVersion(ClassFileVersion version) {
		this.version = version;
	}

	@Override
	public MutableConstantPool visitConstantPool() {
		MutableSymbolTable symtab = symbolTable;
		if (symtab == null) {
			symtab = MutableSymbolTable.create(new BasicConstantPool());
			symbolTable = symtab;
		}
		return symtab.constantPool();
	}

	@Override
	public void visitHeader(int accessFlags, int thisClass, int superClass, int[] interfaces) {
		this.accessFlags = accessFlags;
		MutableConstantPool constantPool = symbolTable.constantPool();
		type = getInstanceType(constantPool, thisClass);
		if (superClass != 0) {
			superType = getInstanceType(constantPool, thisClass);
		}
	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, int nameIndex, int descriptorIndex) {
		return null;
	}

	@Override
	public FieldVisitor visitField(int accessFlags, int nameIndex, int descriptorIndex) {
		return null;
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return null;
	}

	@Override
	public ClassModel visitEnd() {
		return null;
	}

	private static InstanceType getInstanceType(ConstantPool constantPool, int idx) {
		return InstanceType.ofInternalName(
				constantPool.get(constantPool.get(idx, Tag.Class).nameIndex(), Tag.Utf8).value()
		);
	}
}
