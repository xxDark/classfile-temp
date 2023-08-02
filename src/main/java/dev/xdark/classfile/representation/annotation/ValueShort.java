package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueShortImpl;

public interface ValueShort extends AnnotationValue {

	short value();

	static ValueShort create(short value) {
		return new ValueShortImpl(value);
	}
}
