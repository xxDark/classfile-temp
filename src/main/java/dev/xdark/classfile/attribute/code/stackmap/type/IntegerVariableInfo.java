package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.IntegerVariableInfoImpl;

public interface IntegerVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<IntegerVariableInfo> descriptor();

	static IntegerVariableInfo create() {
		return IntegerVariableInfoImpl.INSTANCE;
	}
}
