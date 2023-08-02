package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueLongImpl;

public interface ValueLong extends AnnotationValue {

	long value();

	static ValueLong create(long value) {
		return new ValueLongImpl(value);
	}
}
