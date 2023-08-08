package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.FloatVariableInfoImpl;

public interface FloatVariableInfo extends VerificationTypeInfo {

	static FloatVariableInfo create() {
		return FloatVariableInfoImpl.INSTANCE;
	}
}
