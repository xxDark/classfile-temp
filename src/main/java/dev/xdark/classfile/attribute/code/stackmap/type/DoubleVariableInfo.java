package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.DoubleVariableInfoImpl;

public interface DoubleVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<DoubleVariableInfo> descriptor();

	static DoubleVariableInfo create() {
		return DoubleVariableInfoImpl.INSTANCE;
	}
}
