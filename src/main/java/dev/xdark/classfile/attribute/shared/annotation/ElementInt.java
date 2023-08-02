package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementIntImpl;

public interface ElementInt extends ElementConstant {

	@Override
	ElementDescriptor<ElementInt> descriptor();

	static ElementInt create(int constantIndex) {
		return new ElementIntImpl(constantIndex);
	}
}
