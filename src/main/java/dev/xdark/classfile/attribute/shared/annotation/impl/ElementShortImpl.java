package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementShort;
import dev.xdark.classfile.io.Codec;

public final class ElementShortImpl extends ElementConstantImpl implements ElementShort {

	public ElementShortImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementShort> descriptor() {
		return ElementDescriptor.SHORT;
	}

	public static Codec<ElementShort> codec() {
		return makeCodec(ElementShortImpl::new);
	}
}
