package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementInt;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueInt;

public final class ElementIntImpl extends ElementConstantImpl implements ElementInt, ElementInternal {

	public ElementIntImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementInt> descriptor() {
		return ElementDescriptor.INT;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueInt.create(constantPool.get(constantIndex, Tag.Integer).value());
	}

	public static Codec<ElementInt> codec() {
		return makeCodec(ElementIntImpl::new);
	}
}
