package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.TopVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class TopVariableInfoImpl implements TopVariableInfo {
	private TopVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<TopVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Top;
	}

	public static Codec<TopVariableInfo> codec() {
		return Codec.singleton(new TopVariableInfoImpl());
	}
}
