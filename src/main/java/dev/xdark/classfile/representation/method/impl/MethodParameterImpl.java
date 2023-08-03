package dev.xdark.classfile.representation.method.impl;

import dev.xdark.classfile.representation.method.MethodParameter;

public final class MethodParameterImpl implements MethodParameter {
	private final String name;
	private final int accessFlags;

	public MethodParameterImpl(String name, int accessFlags) {
		this.name = name;
		this.accessFlags = accessFlags;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int accessFlags() {
		return accessFlags;
	}
}
