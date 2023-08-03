package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementShort;
import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueShort;

public final class ValueShortImpl implements ValueShort, ValueInternal {
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

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementShort.create(constantPool.add(ConstantInt.create(value)));
	}
}
