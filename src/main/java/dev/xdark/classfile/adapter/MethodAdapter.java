package dev.xdark.classfile.adapter;

import dev.xdark.classfile.MethodVisitor;
import dev.xdark.classfile.attribute.AttributesVisitor;

public abstract class MethodAdapter implements MethodVisitor {
	protected final MethodVisitor mv;

	protected MethodAdapter(MethodVisitor mv) {
		this.mv = mv;
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return mv.visitAttributes();
	}
}
