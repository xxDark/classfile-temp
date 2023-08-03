package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.method.MethodParameter;
import dev.xdark.classfile.representation.model.CodeModel;
import dev.xdark.classfile.representation.model.MethodModel;
import dev.xdark.classfile.type.MethodType;

import java.util.ArrayList;
import java.util.List;

final class MethodModelImpl extends MemberModelImpl implements MethodModel {
	final List<MethodParameter> parameters = new ArrayList<>();
	CodeModel code;
	AnnotationValue annotationDefault;

	MethodModelImpl(
			int accessFlags,
			String name,
			MethodType type
	) {
		super(accessFlags, name, type);
	}

	@Override
	public MethodType type() {
		return (MethodType) super.type();
	}

	@Override
	public CodeModel code() {
		return code;
	}

	@Override
	public AnnotationValue annotationDefault() {
		return annotationDefault;
	}

	@Override
	public List<MethodParameter> parameters() {
		return parameters;
	}

	@Override
	public void accept(MethodVisitor visitor) {
		super.accept(visitor);
		List<MethodParameter> parameters = this.parameters;
		if (!parameters.isEmpty()) {
			for (MethodParameter parameter : parameters) {
				visitor.visitParameter(parameter);
			}
		}
		CodeModel code = this.code;
		if (code == null) {
			return;
		}
		CodeVisitor cv = visitor.visitCode();
		if (cv != null) {
			code.accept(cv);
		}
	}
}
