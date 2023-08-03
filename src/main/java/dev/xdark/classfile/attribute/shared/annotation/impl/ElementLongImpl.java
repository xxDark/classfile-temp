package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementLong;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueLong;

public final class ElementLongImpl extends ElementConstantImpl implements ElementLong, ElementInternal {

	public ElementLongImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementLong> descriptor() {
		return ElementDescriptor.LONG;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueLong.create(constantPool.get(constantIndex, Tag.Long).value());
	}

	public static Codec<ElementLong> codec() {
		return makeCodec(ElementLongImpl::new);
	}
}
