package dev.xdark.classfile.adapter;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.MethodVisitor;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.ClassVisitor;

public class ClassVisitorSkeleton implements ClassVisitor {

	@Override
	public AttributesVisitor visitAttributes() {
		return null;
	}

	@Override
	public void visitVersion(ClassFileVersion version) {
	}

	@Override
	public MutableConstantPool visitConstantPool() {
		return null;
	}

	@Override
	public void visitHeader(int accessFlags, int thisClass, int superClass, int[] interfaces) {

	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, int nameIndex, int descriptorIndex) {
		return null;
	}

	@Override
	public FieldVisitor visitField(int accessFlags, int nameIndex, int descriptorIndex) {
		return null;
	}
}
