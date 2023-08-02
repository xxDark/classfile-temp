package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementClassImpl;

public interface ElementClass extends Element {

	int nameIndex();

	@Override
	ElementDescriptor<ElementClass> descriptor();

	static ElementClass create(int nameIndex) {
		return new ElementClassImpl(nameIndex);
	}
}
