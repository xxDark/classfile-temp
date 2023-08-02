package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementLongImpl;

public interface ElementLong extends ElementConstant {

	@Override
	ElementDescriptor<ElementLong> descriptor();

	static ElementLong create(int constantIndex) {
		return new ElementLongImpl(constantIndex);
	}
}
