package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueBooleanImpl;

public interface ValueBoolean extends AnnotationValue {

	boolean value();

	static ValueBoolean create(boolean value) {
		return value ? ValueBooleanImpl.TRUE : ValueBooleanImpl.FALSE;
	}
}
