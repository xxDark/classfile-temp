package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementLong;
import dev.xdark.classfile.io.Codec;

public final class ElementLongImpl extends ElementConstantImpl implements ElementLong {

	public ElementLongImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementLong> descriptor() {
		return ElementDescriptor.LONG;
	}

	public static Codec<ElementLong> codec() {
		return makeCodec(ElementLongImpl::new);
	}
}
