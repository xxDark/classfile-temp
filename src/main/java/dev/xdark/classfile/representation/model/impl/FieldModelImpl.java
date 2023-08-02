package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.model.FieldModel;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.type.ClassType;

import java.util.List;

final class FieldModelImpl extends MemberModelImpl implements FieldModel {
	private final LoadableConstant constantValue;

	FieldModelImpl(int accessFlags, String name, ClassType type, String signature, LoadableConstant constantValue, List<UnrecognizedAttribute> unrecognizedAttributes) {
		super(accessFlags, name, type, signature, unrecognizedAttributes);
		this.constantValue = constantValue;
	}

	@Override
	public ClassType type() {
		return (ClassType) super.type();
	}

	@Override
	public LoadableConstant constantValue() {
		return constantValue;
	}
}
