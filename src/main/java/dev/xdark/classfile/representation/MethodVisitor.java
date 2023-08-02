package dev.xdark.classfile.representation;

public interface MethodVisitor extends MemberVisitor {

	CodeVisitor visitCode();
}
