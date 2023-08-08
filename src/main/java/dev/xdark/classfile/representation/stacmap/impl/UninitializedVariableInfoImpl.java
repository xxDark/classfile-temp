package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.stacmap.UninitializedVariableInfo;

public final class UninitializedVariableInfoImpl extends VariableInfoImpl implements UninitializedVariableInfo {
	private final Label location;

	public UninitializedVariableInfoImpl(Label location) {
		this.location = location;
	}

	@Override
	public Label location() {
		return location;
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		throw new UnsupportedOperationException("Should've been handled at call site");
	}
}
