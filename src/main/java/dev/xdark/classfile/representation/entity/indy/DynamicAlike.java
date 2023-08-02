package dev.xdark.classfile.representation.entity.indy;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.type.Type;

import java.util.List;

public interface DynamicAlike {

	String name();

	Type type();

	MethodHandle bootstrapMethodHandle();

	List<LoadableConstant> arguments();
}
