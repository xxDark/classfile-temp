package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueIntImpl;

public interface ValueInt extends AnnotationValue {

	int value();

	static ValueInt create(int value) {
		return new ValueIntImpl(value);
	}
}
