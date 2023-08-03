package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementClass;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueType;
import dev.xdark.classfile.type.Type;

public final class ValueTypeImpl implements ValueType, ValueInternal {
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

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementClass.create(constantPool.add(ConstantUtf8.create(type.descriptor())));
	}
}
