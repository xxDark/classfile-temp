package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.FloatVariableInfoImpl;

public interface FloatVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<FloatVariableInfo> descriptor();

	static FloatVariableInfo create() {
		return FloatVariableInfoImpl.INSTANCE;
	}
}
