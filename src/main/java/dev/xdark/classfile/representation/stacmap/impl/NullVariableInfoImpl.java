package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.NullVariableInfo;

public final class NullVariableInfoImpl extends VariableInfoImpl implements NullVariableInfo {
	public static final NullVariableInfo INSTANCE = new NullVariableInfoImpl();

	private NullVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.NullVariableInfo.create();
	}
}
