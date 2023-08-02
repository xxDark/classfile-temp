package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueType;
import dev.xdark.classfile.type.Type;

public final class ValueTypeImpl implements ValueType {
	private final Type type;

	public ValueTypeImpl(Type type) {
		this.type = type;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptType(this);
	}
}
