package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueLong;

public final class ValueLongImpl implements ValueLong {
	private final long value;

	public ValueLongImpl(long value) {
		this.value = value;
	}

	@Override
	public long value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptLong(this);
	}
}
