package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueFloat;

public final class ValueFloatImpl implements ValueFloat {
	private final float value;

	public ValueFloatImpl(float value) {
		this.value = value;
	}

	@Override
	public float value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptFloat(this);
	}
}
