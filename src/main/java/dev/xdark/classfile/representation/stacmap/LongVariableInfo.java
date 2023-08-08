package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.LongVariableInfoImpl;

public interface LongVariableInfo extends VerificationTypeInfo {

	static LongVariableInfo create() {
		return LongVariableInfoImpl.INSTANCE;
	}
}
