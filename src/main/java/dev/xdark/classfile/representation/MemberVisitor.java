package dev.xdark.classfile.representation;

public interface MemberVisitor extends Attributable, Annotatable {

	void visitSignature(String signature);
}
