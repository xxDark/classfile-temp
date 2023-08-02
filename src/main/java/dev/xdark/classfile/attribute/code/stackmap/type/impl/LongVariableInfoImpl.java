package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.LongVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class LongVariableInfoImpl implements LongVariableInfo {
	private LongVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<LongVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Long;
	}

	public static Codec<LongVariableInfo> codec() {
		return Codec.singleton(new LongVariableInfoImpl());
	}
}
