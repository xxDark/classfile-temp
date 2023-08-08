package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.UninitializedThisVariableInfoImpl;

public interface UninitializedThisVariableInfo extends VerificationTypeInfo {

	static UninitializedThisVariableInfo create() {
		return UninitializedThisVariableInfoImpl.INSTANCE;
	}
}
