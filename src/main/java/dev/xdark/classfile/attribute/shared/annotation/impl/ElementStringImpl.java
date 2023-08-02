package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementString;
import dev.xdark.classfile.io.Codec;

public final class ElementStringImpl extends ElementConstantImpl implements ElementString {

	public ElementStringImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementString> descriptor() {
		return ElementDescriptor.STRING;
	}

	public static Codec<ElementString> codec() {
		return makeCodec(ElementStringImpl::new);
	}
}
