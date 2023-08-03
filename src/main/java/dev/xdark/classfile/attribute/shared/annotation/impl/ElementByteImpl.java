package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementByte;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueByte;

public final class ElementByteImpl extends ElementConstantImpl implements ElementByte, ElementInternal {

	public ElementByteImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementByte> descriptor() {
		return ElementDescriptor.BYTE;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueByte.create((byte) constantPool.get(constantIndex, Tag.Integer).value());
	}

	public static Codec<ElementByte> codec() {
		return makeCodec(ElementByteImpl::new);
	}
}
