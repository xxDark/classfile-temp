package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueDoubleImpl;

public interface ValueDouble extends AnnotationValue {

	double value();

	static ValueDouble create(double value) {
		return new ValueDoubleImpl(value);
	}
}
