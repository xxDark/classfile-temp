package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementClass;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ElementClassImpl implements ElementClass {
	private final int nameIndex;

	public ElementClassImpl(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public ElementDescriptor<ElementClass> descriptor() {
		return ElementDescriptor.CLASS;
	}

	public static Codec<ElementClass> codec() {
		return Codec.wire(
				Input.wire(reader -> new ElementClassImpl(reader.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.nameIndex())
		);
	}
}
