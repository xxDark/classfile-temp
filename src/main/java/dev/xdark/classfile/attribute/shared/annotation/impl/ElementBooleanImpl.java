package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementBoolean;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.io.Codec;

public final class ElementBooleanImpl extends ElementConstantImpl implements ElementBoolean {

	public ElementBooleanImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementBoolean> descriptor() {
		return ElementDescriptor.BOOLEAN;
	}

	public static Codec<ElementBoolean> codec() {
		return makeCodec(ElementBooleanImpl::new);
	}
}
