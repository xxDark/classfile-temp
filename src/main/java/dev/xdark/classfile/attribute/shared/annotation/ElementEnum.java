package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementEnumImpl;

public interface ElementEnum extends Element {

	int typeNameIndex();

	int valueNameIndex();

	@Override
	ElementDescriptor<ElementEnum> descriptor();

	static ElementEnum create(int typeNameIndex, int valueNameIndex) {
		return new ElementEnumImpl(typeNameIndex, valueNameIndex);
	}
}
