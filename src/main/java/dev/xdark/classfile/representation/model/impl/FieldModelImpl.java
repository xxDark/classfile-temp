package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.model.FieldModel;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.type.ClassType;

import java.util.List;

final class FieldModelImpl extends MemberModelImpl implements FieldModel {
	LoadableConstant constantValue;

	FieldModelImpl(
			int accessFlags,
			String name,
			ClassType type
	) {
		super(accessFlags, name, type);
	}

	@Override
	public ClassType type() {
		return (ClassType) super.type();
	}

	@Override
	public LoadableConstant constantValue() {
		return constantValue;
	}

	@Override
	public void accept(FieldVisitor visitor) {
		super.accept(visitor);
		visitor.visitConstantValue(constantValue);
	}
}
