package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementEnum;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ElementEnumImpl implements ElementEnum {
	private final int typeNameIndex;
	private final int valueNameIndex;

	public ElementEnumImpl(int typeNameIndex, int valueNameIndex) {
		this.typeNameIndex = typeNameIndex;
		this.valueNameIndex = valueNameIndex;
	}

	@Override
	public int typeNameIndex() {
		return typeNameIndex;
	}

	@Override
	public int valueNameIndex() {
		return valueNameIndex;
	}

	@Override
	public ElementDescriptor<ElementEnum> descriptor() {
		return ElementDescriptor.ENUM;
	}

	public static Codec<ElementEnum> codec() {
		return Codec.wire(
				Input.wire(reader -> new ElementEnumImpl(reader.readConstantPoolIndex(), reader.readConstantPoolIndex()), ExactSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.typeNameIndex());
					writer.writeConstantPoolIndex(value.valueNameIndex());
				}
		);
	}
}
