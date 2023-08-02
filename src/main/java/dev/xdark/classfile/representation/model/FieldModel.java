package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.type.ClassType;

public interface FieldModel extends MemberModel {

	@Override
	ClassType type();

	LoadableConstant constantValue();
}
