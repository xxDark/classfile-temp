package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.FloatVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class FloatVariableInfoImpl implements FloatVariableInfo {
	private FloatVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<FloatVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Float;
	}

	public static Codec<FloatVariableInfo> codec() {
		return Codec.singleton(new FloatVariableInfoImpl());
	}
}
