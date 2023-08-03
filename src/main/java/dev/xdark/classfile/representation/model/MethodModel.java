package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.method.MethodParameter;
import dev.xdark.classfile.type.MethodType;

import java.util.List;

public interface MethodModel extends MemberModel {

	@Override
	MethodType type();

	CodeModel code();

	AnnotationValue annotationDefault();

	List<MethodParameter> parameters();

	void accept(MethodVisitor visitor);
}
