package dev.xdark.classfile.representation.entity.indy;

import dev.xdark.classfile.representation.entity.indy.impl.MethodHandleImpl;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.ReferenceKind;
import dev.xdark.classfile.type.Type;

public interface MethodHandle {

	ReferenceKind kind();

	ObjectType owner();

	String name();

	Type type();

	boolean isInterface();

	static MethodHandle create(ReferenceKind kind, ObjectType owner, String name, Type type, boolean isInterface) {
		return new MethodHandleImpl(kind, owner, name, type, isInterface);
	}
}
