package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementByte;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.io.Codec;

public final class ElementByteImpl extends ElementConstantImpl implements ElementByte {

	public ElementByteImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementByte> descriptor() {
		return ElementDescriptor.BYTE;
	}

	public static Codec<ElementByte> codec() {
		return makeCodec(ElementByteImpl::new);
	}
}
