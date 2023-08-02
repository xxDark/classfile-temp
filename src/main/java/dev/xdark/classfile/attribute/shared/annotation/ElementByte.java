package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementByteImpl;

public interface ElementByte extends ElementConstant {

	@Override
	ElementDescriptor<ElementByte> descriptor();

	static ElementByte create(int constantIndex) {
		return new ElementByteImpl(constantIndex);
	}
}
