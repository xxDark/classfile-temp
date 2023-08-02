package dev.xdark.classfile.representation.entity.indy.impl;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.Type;

import java.util.List;

public final class ConstantDynamicImpl extends DynamicAlikeImpl implements ConstantDynamic {

	public ConstantDynamicImpl(String name, Type type, MethodHandle methodHandle, List<LoadableConstant> arguments) {
		super(name, type, methodHandle, arguments);
	}
}
