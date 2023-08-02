package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementDouble;
import dev.xdark.classfile.io.Codec;

public final class ElementDoubleImpl extends ElementConstantImpl implements ElementDouble {

	public ElementDoubleImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementDouble> descriptor() {
		return ElementDescriptor.DOUBLE;
	}

	public static Codec<ElementDouble> codec() {
		return makeCodec(ElementDoubleImpl::new);
	}
}
