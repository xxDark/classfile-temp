package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.UninitializedThisVariableInfo;

public final class UninitializedThisVariableInfoImpl extends VariableInfoImpl implements UninitializedThisVariableInfo {
	public static final UninitializedThisVariableInfo INSTANCE = new UninitializedThisVariableInfoImpl();

	private UninitializedThisVariableInfoImpl() {
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.UninitializedThisVariableInfo.create();
	}
}
