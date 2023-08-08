package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.IntegerVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.IntVariableInfo;

public final class IntVariableInfoImpl extends VariableInfoImpl implements IntVariableInfo {
	public static final IntVariableInfo INSTANCE = new IntVariableInfoImpl();

	private IntVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return IntegerVariableInfo.create();
	}
}
