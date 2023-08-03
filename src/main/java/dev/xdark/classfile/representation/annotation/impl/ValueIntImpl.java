package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementByte;
import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueInt;

public final class ValueIntImpl implements ValueInt, ValueInternal {
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

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementByte.create(constantPool.add(ConstantInt.create(value)));
	}
}
