package dev.xdark.classfile.adapter;

import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.attribute.AttributesVisitor;

public abstract class FieldAdapter implements FieldVisitor {
	protected final FieldVisitor fv;

	protected FieldAdapter(FieldVisitor fv) {
		this.fv = fv;
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return fv.visitAttributes();
	}
}
