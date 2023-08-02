package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementFloat;
import dev.xdark.classfile.io.Codec;

public final class ElementFloatImpl extends ElementConstantImpl implements ElementFloat {

	public ElementFloatImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementFloat> descriptor() {
		return ElementDescriptor.FLOAT;
	}

	public static Codec<ElementFloat> codec() {
		return makeCodec(ElementFloatImpl::new);
	}
}
