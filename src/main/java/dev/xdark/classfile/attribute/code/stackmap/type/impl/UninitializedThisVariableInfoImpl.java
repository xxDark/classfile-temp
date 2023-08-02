package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.TopVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.UninitializedThisVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;

public final class UninitializedThisVariableInfoImpl implements UninitializedThisVariableInfo {
	private UninitializedThisVariableInfoImpl() {
	}

	@Override
	public VerificationTypeDescriptor<UninitializedThisVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_UninitializedThis;
	}

	public static Codec<UninitializedThisVariableInfo> codec() {
		return Codec.singleton(new UninitializedThisVariableInfoImpl());
	}
}
