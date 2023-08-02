package dev.xdark.classfile.attribute.field.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.field.ConstantValueAttribute;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class ConstantValueAttributeImpl implements ConstantValueAttribute {
	private final int constantIndex;

	ConstantValueAttributeImpl(int constantIndex) {
		this.constantIndex = constantIndex;
	}

	@Override
	public int constantIndex() {
		return constantIndex;
	}

	@Override
	public AttributeInfo<ConstantValueAttribute> info() {
		return AttributeInfo.ConstantValue;
	}

	public static Codec<ConstantValueAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 2L);
					return new ConstantValueAttributeImpl(reader.readConstantPoolIndex());
				}, VariableSkip.U4),
				(writer, value) -> writer.writeConstantPoolIndex(value.constantIndex())
		);
	}
}
