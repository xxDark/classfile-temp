package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementByte;
import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueByte;

public final class ValueByteImpl implements ValueByte, ValueInternal {
	private static final ValueByte[] CACHE;
	private final byte value;

	private ValueByteImpl(byte value) {
		this.value = value;
	}

	@Override
	public byte value() {
		return value;
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptByte(this);
	}

	@Override
	public Element denormalize(MutableConstantPool constantPool) {
		return ElementByte.create(constantPool.add(ConstantInt.create(value)));
	}

	public static ValueByte get(byte b) {
		return CACHE[b & 0xff];
	}

	static {
		ValueByte[] cache = new ValueByte[256];
		for (int i = 0; i < 256; i++) {
			cache[i] = new ValueByteImpl((byte) i);
		}
		CACHE = cache;
	}
}
