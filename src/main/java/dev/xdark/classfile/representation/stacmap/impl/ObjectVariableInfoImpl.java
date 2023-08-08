package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.ConstantClass;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.stacmap.ObjectVariableInfo;
import dev.xdark.classfile.type.ObjectType;

public final class ObjectVariableInfoImpl extends VariableInfoImpl implements ObjectVariableInfo {
	private final ObjectType type;

	public ObjectVariableInfoImpl(ObjectType type) {
		this.type = type;
	}

	@Override
	public ObjectType type() {
		return type;
	}

	@Override
	public VerificationTypeInfo denormalize(MutableConstantPool constantPool) {
		return dev.xdark.classfile.attribute.code.stackmap.type.ObjectVariableInfo.create(
				constantPool.add(ConstantClass.create(constantPool.add(ConstantUtf8.create(type.internalName()))))
		);
	}
}
