package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.NestHostAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class NestHostAttributeImpl implements NestHostAttribute {

	private final int hostClassIndex;

	NestHostAttributeImpl(int hostClassIndex) {
		this.hostClassIndex = hostClassIndex;
	}

	@Override
	public int hostClassIndex() {
		return hostClassIndex;
	}

	@Override
	public AttributeInfo<NestHostAttribute> info() {
		return AttributeInfo.NestHost;
	}

	public static Codec<NestHostAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 2L);
					return new NestHostAttributeImpl(reader.readConstantPoolIndex());
				}, VariableSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.hostClassIndex());
				}
		);
	}
}
