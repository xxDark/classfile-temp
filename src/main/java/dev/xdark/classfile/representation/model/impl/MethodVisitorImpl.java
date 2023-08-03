package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.GeneralAnnotationValueSink;
import dev.xdark.classfile.representation.method.MethodParameter;
import dev.xdark.classfile.representation.model.CodeModel;

final class MethodVisitorImpl implements MethodVisitor {
	private final MethodModelImpl methodModel;

	MethodVisitorImpl(MethodModelImpl methodModel) {
		this.methodModel = methodModel;
	}

	@Override
	public CodeVisitor visitCode() {
		MethodModelImpl mm = methodModel;
		CodeModel cm = mm.code;
		if (cm == null) {
			cm = new CodeModelImpl();
			mm.code = cm;
		}
		return new CodeVisitorImpl((CodeModelImpl) cm);
	}

	@Override
	public void visitSignature(String signature) {
		methodModel.signature = signature;
	}

	@Override
	public AnnotationsVisitor visitVisibleRuntimeAnnotations() {
		return new AnnotationsCollector(methodModel.visibleRuntimeAnnotations());
	}

	@Override
	public AnnotationsVisitor visitInvisibleRuntimeAnnotations() {
		return new AnnotationsCollector(methodModel.invisibleRuntimeAnnotations());
	}

	@Override
	public AnnotationValueSink annotationDefaultSink() {
		return new GeneralAnnotationValueSink() {
			@Override
			protected void accept(AnnotationValue value) {
				methodModel.annotationDefault = value;
			}
		};
	}

	@Override
	public void visitParameter(MethodParameter parameter) {
		methodModel.parameters.add(parameter);
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return methodModel.unrecognizedAttributes()::add;
	}
}
