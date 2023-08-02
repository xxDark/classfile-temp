package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.type.InstanceType;

import java.util.Map;

public interface AnnotationContainer extends AnnotationValue {

	InstanceType type();

	Map<String, AnnotationValue> values();

	void accept(AnnotationContainerVisitor visitor);
}
