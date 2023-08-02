package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public interface FieldVisitor extends MemberVisitor {

	void visitConstantValue(LoadableConstant constant);
}
