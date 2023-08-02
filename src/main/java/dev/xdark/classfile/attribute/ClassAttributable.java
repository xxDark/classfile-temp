package dev.xdark.classfile.attribute;

public interface ClassAttributable extends Attributable {

	void visitSourceFile(int nameIndex);
}
