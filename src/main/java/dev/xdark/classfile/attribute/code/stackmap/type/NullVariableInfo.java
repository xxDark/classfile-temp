package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.NullVariableInfoImpl;

public interface NullVariableInfo extends VerificationTypeInfo {

	@Override
	VerificationTypeDescriptor<NullVariableInfo> descriptor();

	static NullVariableInfo create() {
		return NullVariableInfoImpl.INSTANCE;
	}
}
