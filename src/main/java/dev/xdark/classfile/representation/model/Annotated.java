package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.annotation.AnnotationContainer;

import java.util.List;

public interface Annotated {

	List<AnnotationContainer> visibleRuntimeAnnotations();

	List<AnnotationContainer> invisibleRuntimeAnnotations();
}
