package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementArrayImpl;

import java.util.List;

public interface ElementArray extends Element {

	List<Element> elements();

	void accept(ArrayVisitor visitor);

	@Override
	ElementDescriptor<ElementArray> descriptor();

	static ElementArray create(List<Element> elements) {
		return new ElementArrayImpl(elements);
	}
}
