package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.DoubleVariableInfo;

public final class DoubleVariableInfoImpl extends VariableInfoImpl implements DoubleVariableInfo {
	public static final DoubleVariableInfo INSTANCE = new DoubleVariableInfoImpl();

	private DoubleVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.DoubleVariableInfo.create();
	}
}
