package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementLong;
import dev.xdark.classfile.constantpool.ConstantLong;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueLong;

public final class ValueLongImpl implements ValueLong, ValueInternal {
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

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementLong.create(constantPool.add(ConstantLong.create(value)));
	}
}
