package dev.xdark.classfile;

import dev.xdark.classfile.attribute.Attributable;
import dev.xdark.classfile.constantpool.MutableConstantPool;

public interface ClassVisitor extends Attributable {

	void visitVersion(ClassFileVersion version);

	MutableConstantPool visitConstantPool();

	void visitHeader(int accessFlags, int thisClass, int superClass, int[] interfaces);

	MethodVisitor visitMethod(int accessFlags, int nameIndex, int descriptorIndex);

	FieldVisitor visitField(int accessFlags, int nameIndex, int descriptorIndex);
}
