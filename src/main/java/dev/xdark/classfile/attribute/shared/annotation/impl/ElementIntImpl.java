package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementInt;
import dev.xdark.classfile.io.Codec;

public final class ElementIntImpl extends ElementConstantImpl implements ElementInt {

	public ElementIntImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementInt> descriptor() {
		return ElementDescriptor.INT;
	}

	public static Codec<ElementInt> codec() {
		return makeCodec(ElementIntImpl::new);
	}
}
