package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.LongVariableInfo;

public final class LongVariableInfoImpl extends VariableInfoImpl implements LongVariableInfo {
	public static final LongVariableInfo INSTANCE = new LongVariableInfoImpl();

	private LongVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.LongVariableInfo.create();
	}
}
