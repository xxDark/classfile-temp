package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementCharImpl;

public interface ElementChar extends ElementConstant {

	@Override
	ElementDescriptor<ElementChar> descriptor();

	static ElementChar create(int constantIndex) {
		return new ElementCharImpl(constantIndex);
	}
}
