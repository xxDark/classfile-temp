package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.NullVariableInfoImpl;

public interface NullVariableInfo extends VerificationTypeInfo {

	static NullVariableInfo create() {
		return NullVariableInfoImpl.INSTANCE;
	}
}
