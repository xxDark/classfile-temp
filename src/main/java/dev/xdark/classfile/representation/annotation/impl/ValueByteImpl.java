package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueByte;

public final class ValueByteImpl implements ValueByte {
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
