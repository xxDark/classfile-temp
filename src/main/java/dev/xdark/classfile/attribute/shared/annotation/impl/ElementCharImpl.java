package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementChar;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.io.Codec;

public final class ElementCharImpl extends ElementConstantImpl implements ElementChar {

	public ElementCharImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementChar> descriptor() {
		return ElementDescriptor.CHAR;
	}

	public static Codec<ElementChar> codec() {
		return makeCodec(ElementCharImpl::new);
	}
}
