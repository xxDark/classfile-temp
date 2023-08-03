package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.Attributed;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.representation.annotation.AnnotationContainer;
import dev.xdark.classfile.representation.model.Annotated;

import java.util.List;

class BaseImpl implements Attributed, Annotated {
	private final List<UnrecognizedAttribute> unrecognizedAttributes;
	private final List<AnnotationContainer> visibleRuntimeAnnotations;
	private final List<AnnotationContainer> invisibleRuntimeAnnotations;

	protected BaseImpl(List<AnnotationContainer> visibleRuntimeAnnotations, List<AnnotationContainer> invisibleRuntimeAnnotations, List<UnrecognizedAttribute> unrecognizedAttributes) {
		this.visibleRuntimeAnnotations = visibleRuntimeAnnotations;
		this.invisibleRuntimeAnnotations = invisibleRuntimeAnnotations;
		this.unrecognizedAttributes = unrecognizedAttributes;
	}

	@Override
	public List<UnrecognizedAttribute> unrecognizedAttributes() {
		return unrecognizedAttributes;
	}

	@Override
	public final List<AnnotationContainer> visibleRuntimeAnnotations() {
		return visibleRuntimeAnnotations;
	}

	@Override
	public final List<AnnotationContainer> invisibleRuntimeAnnotations() {
		return invisibleRuntimeAnnotations;
	}
}
