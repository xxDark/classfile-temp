package dev.xdark.classfile.representation.entity.indy;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.impl.ConstantDynamicImpl;
import dev.xdark.classfile.type.Type;

import java.util.List;

public interface ConstantDynamic extends DynamicAlike {

	static ConstantDynamic create(String name, Type type, MethodHandle methodHandle, List<LoadableConstant> arguments) {
		return new ConstantDynamicImpl(name, type, methodHandle, arguments);
	}
}
