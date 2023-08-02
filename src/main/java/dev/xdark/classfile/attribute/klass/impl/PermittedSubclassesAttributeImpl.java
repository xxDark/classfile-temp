package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.PermittedSubclassesAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class PermittedSubclassesAttributeImpl implements PermittedSubclassesAttribute {
	private final int[] classIndices;

	public PermittedSubclassesAttributeImpl(int[] classIndices) {
		this.classIndices = classIndices;
	}

	@Override
	public int[] classIndices() {
		return classIndices;
	}

	@Override
	public AttributeInfo<PermittedSubclassesAttribute> info() {
		return AttributeInfo.PermittedSubclasses;
	}

	public static Codec<PermittedSubclassesAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new PermittedSubclassesAttributeImpl(AttributeHelper.readUnsignedShorts(reader));
				}, VariableSkip.U4),
				(writer, value) -> AttributeHelper.writeUnsignedShorts(writer, value.classIndices())
		);
	}
}
