package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueCharImpl;

public interface ValueChar extends AnnotationValue {

	char value();

	static ValueChar create(char value) {
		return new ValueCharImpl(value);
	}
}
