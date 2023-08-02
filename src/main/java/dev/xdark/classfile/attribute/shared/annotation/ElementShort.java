package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementShortImpl;

public interface ElementShort extends ElementConstant {

	@Override
	ElementDescriptor<ElementShort> descriptor();

	static ElementShort create(int constantIndex) {
		return new ElementShortImpl(constantIndex);
	}
}
