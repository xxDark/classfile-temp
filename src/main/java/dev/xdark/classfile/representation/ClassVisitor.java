package dev.xdark.classfile.representation;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;

import java.util.List;

public interface ClassVisitor extends Attributable {

	void visitVersion(ClassFileVersion version);

	void visitSymbolTable(SymbolTable symbolTable);

	void visitHeader(int accessFlags, InstanceType thisClass, InstanceType superClass, List<InstanceType> interfaces);

	FieldVisitor visitField(int accessFlags, String name, ClassType type);

	MethodVisitor visitMethod(int accessFlags, String name, MethodType type);

	void visitSignature(String signature);
}
