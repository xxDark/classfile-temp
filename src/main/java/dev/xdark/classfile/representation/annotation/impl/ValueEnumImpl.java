package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementEnum;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueEnum;
import dev.xdark.classfile.type.InstanceType;

public final class ValueEnumImpl implements ValueEnum, ValueInternal {
	private final InstanceType type;
	private final String name;

	public ValueEnumImpl(InstanceType type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public InstanceType type() {
		return type;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptEnum(this);
	}

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementEnum.create(
				constantPool.add(ConstantUtf8.create(type.descriptor())),
				constantPool.add(ConstantUtf8.create(name))
		);
	}
}
