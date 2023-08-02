package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueStringImpl;

public interface ValueString extends AnnotationValue {

	String value();

	static ValueString create(String value) {
		return new ValueStringImpl(value);
	}
}
