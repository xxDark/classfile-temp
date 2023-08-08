package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.UninitializedThisVariableInfoImpl;

public interface UninitializedThisVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<UninitializedThisVariableInfo> descriptor();

	static UninitializedThisVariableInfo create() {
		return UninitializedThisVariableInfoImpl.INSTANCE;
	}
}
