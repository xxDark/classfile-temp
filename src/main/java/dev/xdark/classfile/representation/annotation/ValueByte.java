package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueByteImpl;

public interface ValueByte extends AnnotationValue {

	byte value();

	static ValueByte create(byte value) {
		return ValueByteImpl.get(value);
	}
}
