package dev.xdark.classfile.attribute.method.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.method.ExceptionsAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class ExceptionsAttributeImpl implements ExceptionsAttribute {
	private final int[] exceptionIndices;

	public ExceptionsAttributeImpl(int[] exceptionIndices) {
		this.exceptionIndices = exceptionIndices;
	}

	@Override
	public int[] exceptionIndices() {
		return exceptionIndices;
	}

	@Override
	public AttributeInfo<ExceptionsAttribute> info() {
		return AttributeInfo.Exceptions;
	}

	public static Codec<ExceptionsAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new ExceptionsAttributeImpl(AttributeHelper.readUnsignedShorts(reader));
				}, VariableSkip.U4),
				(writer, value) -> AttributeHelper.writeUnsignedShorts(writer, value.exceptionIndices())
		);
	}
}
