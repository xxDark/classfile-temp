package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementFloat;
import dev.xdark.classfile.constantpool.ConstantFloat;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueFloat;

public final class ValueFloatImpl implements ValueFloat, ValueInternal {
	private final float value;

	public ValueFloatImpl(float value) {
		this.value = value;
	}

	@Override
	public float value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptFloat(this);
	}

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementFloat.create(constantPool.add(ConstantFloat.create(value)));
	}
}
