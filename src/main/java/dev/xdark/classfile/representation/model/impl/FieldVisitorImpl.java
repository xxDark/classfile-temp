package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

final class FieldVisitorImpl implements FieldVisitor {
	private final FieldModelImpl fieldModel;

	FieldVisitorImpl(FieldModelImpl fieldModel) {
		this.fieldModel = fieldModel;
	}

	@Override
	public void visitConstantValue(LoadableConstant constant) {
		fieldModel.constantValue = constant;
	}

	@Override
	public void visitSignature(String signature) {
		fieldModel.signature = signature;
	}

	@Override
	public AnnotationsVisitor visitVisibleRuntimeAnnotations() {
		return new AnnotationsCollector(fieldModel.visibleRuntimeAnnotations());
	}

	@Override
	public AnnotationsVisitor visitInvisibleRuntimeAnnotations() {
		return new AnnotationsCollector(fieldModel.invisibleRuntimeAnnotations());
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return fieldModel.unrecognizedAttributes()::add;
	}
}
