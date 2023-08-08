package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.LongVariableInfoImpl;

public interface LongVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<LongVariableInfo> descriptor();

	static LongVariableInfo create() {
		return LongVariableInfoImpl.INSTANCE;
	}
}
