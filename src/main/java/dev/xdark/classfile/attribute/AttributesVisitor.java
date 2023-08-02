package dev.xdark.classfile.attribute;

import java.io.IOException;

public interface AttributesVisitor {

	void visit(int nameIndex, SpecAttribute attribute);

	void visit(int nameIndex, UnknownAttribute attribute) throws IOException;
}
