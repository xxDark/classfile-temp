package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.TopVariableInfoImpl;

public interface TopVariableInfo extends VerificationTypeInfo {

	static TopVariableInfo create() {
		return TopVariableInfoImpl.INSTANCE;
	}
}
