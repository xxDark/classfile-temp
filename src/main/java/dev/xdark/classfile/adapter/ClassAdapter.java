package dev.xdark.classfile.adapter;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.MethodVisitor;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.ClassVisitor;

public abstract class ClassAdapter implements ClassVisitor {
	protected final ClassVisitor cv;

	protected ClassAdapter(ClassVisitor cv) {
		this.cv = cv;
	}

	@Override
	public void visitVersion(ClassFileVersion version) {
		cv.visitVersion(version);
	}

	@Override
	public MutableConstantPool visitConstantPool() {
		return cv.visitConstantPool();
	}

	@Override
	public void visitHeader(int accessFlags, int thisClass, int superClass, int[] interfaces) {
		cv.visitHeader(accessFlags, thisClass, superClass, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, int nameIndex, int descriptorIndex) {
		return cv.visitMethod(accessFlags, nameIndex, descriptorIndex);
	}

	@Override
	public FieldVisitor visitField(int accessFlags, int nameIndex, int descriptorIndex) {
		return cv.visitField(accessFlags, nameIndex, descriptorIndex);
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return cv.visitAttributes();
	}
}
