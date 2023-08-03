package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueDouble;

public final class ValueDoubleImpl implements ValueDouble {
	private final double value;

	public ValueDoubleImpl(double value) {
		this.value = value;
	}

	@Override
	public double value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptDouble(this);
	}
}
