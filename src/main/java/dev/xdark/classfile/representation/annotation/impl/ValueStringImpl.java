package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueString;

public final class ValueStringImpl implements ValueString {
	private static final ValueString EMPTY = new ValueStringImpl("");
	private final String value;

	public ValueStringImpl(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptString(this);
	}

	public static ValueString get(String value) {
		return value.isEmpty() ? EMPTY : new ValueStringImpl(value);
	}
}
