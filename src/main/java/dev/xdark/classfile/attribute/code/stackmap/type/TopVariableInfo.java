package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.TopVariableInfoImpl;

public interface TopVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<TopVariableInfo> descriptor();

	static TopVariableInfo create() {
		return TopVariableInfoImpl.INSTANCE;
	}
}
