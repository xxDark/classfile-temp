package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementFloatImpl;

public interface ElementFloat extends ElementConstant {

	@Override
	ElementDescriptor<ElementFloat> descriptor();

	static ElementFloat create(int constantIndex) {
		return new ElementFloatImpl(constantIndex);
	}
}
