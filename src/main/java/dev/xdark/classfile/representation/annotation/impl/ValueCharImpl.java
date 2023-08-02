package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueChar;

public final class ValueCharImpl implements ValueChar {
	private final char value;

	public ValueCharImpl(char value) {
		this.value = value;
	}

	@Override
	public char value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptChar(this);
	}
}
