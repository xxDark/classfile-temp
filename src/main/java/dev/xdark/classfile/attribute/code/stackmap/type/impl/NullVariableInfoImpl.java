package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.FloatVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.NullVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class NullVariableInfoImpl implements NullVariableInfo {
	private NullVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<NullVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Null;
	}

	public static Codec<NullVariableInfo> codec() {
		return Codec.singleton(new NullVariableInfoImpl());
	}
}
