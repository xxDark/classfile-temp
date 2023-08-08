package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.FloatVariableInfo;

public final class FloatVariableInfoImpl extends VariableInfoImpl implements FloatVariableInfo {
	public static final FloatVariableInfo INSTANCE = new FloatVariableInfoImpl();

	private FloatVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.FloatVariableInfo.create();
	}
}
