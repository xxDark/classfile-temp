package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueShort;

public final class ValueShortImpl implements ValueShort {
	private final short value;

	public ValueShortImpl(short value) {
		this.value = value;
	}

	@Override
	public short value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptShort(this);
	}
}
