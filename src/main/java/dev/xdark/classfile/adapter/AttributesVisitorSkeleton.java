package dev.xdark.classfile.adapter;

import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;

import java.io.IOException;

public class AttributesVisitorSkeleton implements AttributesVisitor {

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
	}

	@Override
	public void visit(int nameIndex, UnknownAttribute attribute) {
	}
}
