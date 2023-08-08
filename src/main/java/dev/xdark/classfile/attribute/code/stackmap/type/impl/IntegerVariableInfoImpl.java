package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.IntegerVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class IntegerVariableInfoImpl implements IntegerVariableInfo {
	public static final IntegerVariableInfo INSTANCE = new IntegerVariableInfoImpl();

	private IntegerVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<IntegerVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Integer;
	}

	public static Codec<IntegerVariableInfo> codec() {
		return Codec.singleton(INSTANCE);
	}
}
