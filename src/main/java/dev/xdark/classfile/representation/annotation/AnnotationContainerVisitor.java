package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.type.InstanceType;

public interface AnnotationContainerVisitor {

	void visit(String name, AnnotationValue value);

	AnnotationContainerVisitor visitAnnotation(String name, InstanceType type);

	ValueArrayVisitor visitArray(String name);
}
