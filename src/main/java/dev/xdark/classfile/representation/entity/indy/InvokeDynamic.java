package dev.xdark.classfile.representation.entity.indy;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.impl.InvokeDynamicImpl;
import dev.xdark.classfile.type.Type;

import java.util.List;

public interface InvokeDynamic extends DynamicAlike {

	static InvokeDynamic create(String name, Type type, MethodHandle methodHandle, List<LoadableConstant> arguments) {
		return new InvokeDynamicImpl(name, type, methodHandle, arguments);
	}
}
