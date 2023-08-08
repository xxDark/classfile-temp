package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.stacmap.impl.UninitializedVariableInfoImpl;

public interface UninitializedVariableInfo extends VerificationTypeInfo {

	Label location();

	static UninitializedVariableInfo create(Label label) {
		return new UninitializedVariableInfoImpl(label);
	}
}
