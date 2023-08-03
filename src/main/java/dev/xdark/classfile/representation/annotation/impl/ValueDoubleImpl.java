package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementDouble;
import dev.xdark.classfile.constantpool.ConstantDouble;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueDouble;

public final class ValueDoubleImpl implements ValueDouble, ValueInternal {
	private final double value;

	public ValueDoubleImpl(double value) {
		this.value = value;
	}

	@Override
	public double value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptDouble(this);
	}

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementDouble.create(constantPool.add(ConstantDouble.create(value)));
	}
}
