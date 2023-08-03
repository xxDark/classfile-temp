package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementInt;
import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueBoolean;

public final class ValueBooleanImpl implements ValueBoolean, ValueInternal {
	public static final ValueBoolean TRUE = new ValueBooleanImpl(true),
			FALSE = new ValueBooleanImpl(false);
	private final boolean value;

	private ValueBooleanImpl(boolean value) {
		this.value = value;
	}

	@Override
	public boolean value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptBoolean(this);
	}

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementInt.create(constantPool.add(ConstantInt.create(value ? 1 : 0)));
	}
}
