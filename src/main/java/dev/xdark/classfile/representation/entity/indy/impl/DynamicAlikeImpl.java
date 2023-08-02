package dev.xdark.classfile.representation.entity.indy.impl;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.DynamicAlike;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.Type;

import java.util.List;

abstract class DynamicAlikeImpl implements DynamicAlike {
	private final String name;
	private final Type type;
	private final MethodHandle methodHandle;
	private final List<LoadableConstant> arguments;

	public DynamicAlikeImpl(String name, Type type, MethodHandle methodHandle, List<LoadableConstant> arguments) {
		this.name = name;
		this.type = type;
		this.methodHandle = methodHandle;
		this.arguments = arguments;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public MethodHandle bootstrapMethodHandle() {
		return methodHandle;
	}

	@Override
	public List<LoadableConstant> arguments() {
		return arguments;
	}
}
