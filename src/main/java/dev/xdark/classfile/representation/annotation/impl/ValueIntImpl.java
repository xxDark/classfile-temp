package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueInt;

public final class ValueIntImpl implements ValueInt {
	private final int value;

	public ValueIntImpl(int value) {
		this.value = value;
	}

	@Override
	public int value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptInt(this);
	}
}
