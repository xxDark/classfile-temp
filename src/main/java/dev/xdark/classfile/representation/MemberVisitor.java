package dev.xdark.classfile.representation;

public interface MemberVisitor extends Attributable {

	void visitSignature(String signature);
}
