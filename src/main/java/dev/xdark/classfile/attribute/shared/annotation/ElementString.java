package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementStringImpl;

public interface ElementString extends ElementConstant {

	@Override
	ElementDescriptor<ElementString> descriptor();

	static ElementString create(int constantIndex) {
		return new ElementStringImpl(constantIndex);
	}
}
