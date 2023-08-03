package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementString;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueString;

public final class ValueStringImpl implements ValueString, ValueInternal {
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

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementString.create(constantPool.add(ConstantUtf8.create(value)));
	}

	public static ValueString get(String value) {
		return value.isEmpty() ? EMPTY : new ValueStringImpl(value);
	}
}
