package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueFloatImpl;

public interface ValueFloat extends AnnotationValue {

	float value();

	static ValueFloat create(float value) {
		return new ValueFloatImpl(value);
	}
}
