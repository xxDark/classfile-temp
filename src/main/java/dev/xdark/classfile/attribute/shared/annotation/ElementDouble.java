package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementDoubleImpl;

public interface ElementDouble extends ElementConstant {

	@Override
	ElementDescriptor<ElementDouble> descriptor();

	static ElementDouble create(int constantIndex) {
		return new ElementDoubleImpl(constantIndex);
	}
}
