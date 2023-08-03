package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementClass;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueType;
import dev.xdark.classfile.type.Type;

public final class ElementClassImpl implements ElementClass, ElementInternal {
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

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueType.create(Type.ofDescriptor(constantPool.get(nameIndex, Tag.Utf8).value()));
	}

	public static Codec<ElementClass> codec() {
		return Codec.wire(
				Input.wire(reader -> new ElementClassImpl(reader.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.nameIndex())
		);
	}
}
