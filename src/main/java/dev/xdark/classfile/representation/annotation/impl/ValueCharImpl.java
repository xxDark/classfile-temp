package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementChar;
import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueChar;

public final class ValueCharImpl implements ValueChar, ValueInternal {
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

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementChar.create(constantPool.add(ConstantInt.create(value)));
	}
}
