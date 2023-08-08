package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.TopVariableInfo;

public final class TopVariableInfoImpl extends VariableInfoImpl implements TopVariableInfo {
	public static final TopVariableInfo INSTANCE = new TopVariableInfoImpl();

	private TopVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.TopVariableInfo.create();
	}
}
