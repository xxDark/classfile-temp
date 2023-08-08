package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.DoubleVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class DoubleVariableInfoImpl implements DoubleVariableInfo {
	public static final DoubleVariableInfo INSTANCE = new DoubleVariableInfoImpl();

	public DoubleVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<DoubleVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Double;
	}

	public static Codec<DoubleVariableInfo> codec() {
		return Codec.singleton(INSTANCE);
	}
}
