package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.IntVariableInfoImpl;

public interface IntVariableInfo extends VerificationTypeInfo {

	static IntVariableInfo create() {
		return IntVariableInfoImpl.INSTANCE;
	}
}
