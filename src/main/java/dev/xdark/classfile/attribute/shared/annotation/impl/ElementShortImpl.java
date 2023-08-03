package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementShort;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueShort;

public final class ElementShortImpl extends ElementConstantImpl implements ElementShort, ElementInternal {

	public ElementShortImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementShort> descriptor() {
		return ElementDescriptor.SHORT;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueShort.create((short) constantPool.get(constantIndex, Tag.Integer).value());
	}

	public static Codec<ElementShort> codec() {
		return makeCodec(ElementShortImpl::new);
	}
}
