package dev.xdark.classfile.representation.method;

import dev.xdark.classfile.representation.method.impl.MethodParameterImpl;

public interface MethodParameter {

	String name();

	int accessFlags();

	static MethodParameter create(String name, int accessFlags) {
		return new MethodParameterImpl(name, accessFlags);
	}
}
