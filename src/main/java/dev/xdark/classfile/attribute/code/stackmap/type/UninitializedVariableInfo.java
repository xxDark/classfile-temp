package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.UninitializedVariableInfoImpl;

public interface UninitializedVariableInfo extends VerificationTypeInfo {

	int offset();

	@Override
	VerificationTypeDescriptor<UninitializedVariableInfo> descriptor();

	static UninitializedVariableInfo create(int offset) {
		return new UninitializedVariableInfoImpl(offset);
	}
}
