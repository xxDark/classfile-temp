package dev.xdark.classfile.attribute;

public interface AttributesVisitor {

	void visit(int nameIndex, SpecAttribute attribute);

	void visit(int nameIndex, UnknownAttribute attribute);
}
