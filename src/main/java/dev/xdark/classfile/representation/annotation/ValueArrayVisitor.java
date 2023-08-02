package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.type.InstanceType;

public interface ValueArrayVisitor {

	void visit(AnnotationValue value);

	AnnotationContainerVisitor visitAnnotation(InstanceType type);

	ValueArrayVisitor visitArray();
}
