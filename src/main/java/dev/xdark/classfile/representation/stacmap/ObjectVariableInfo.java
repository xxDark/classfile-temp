package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.stacmap.impl.ObjectVariableInfoImpl;
import dev.xdark.classfile.type.ObjectType;

public interface ObjectVariableInfo extends VerificationTypeInfo {

	ObjectType type();

	static ObjectVariableInfo create(ObjectType type) {
		return new ObjectVariableInfoImpl(type);
	}
}
