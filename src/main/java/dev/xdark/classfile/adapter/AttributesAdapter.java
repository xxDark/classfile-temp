package dev.xdark.classfile.adapter;

import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;

import java.io.IOException;

public abstract class AttributesAdapter implements AttributesVisitor {
	protected final AttributesVisitor av;

	protected AttributesAdapter(AttributesVisitor av) {
		this.av = av;
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		av.visit(nameIndex, attribute);
	}

	@Override
	public void visit(int nameIndex, UnknownAttribute attribute) throws IOException {
		av.visit(nameIndex, attribute);
	}
}
