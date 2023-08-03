package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementAnnotationImpl;

import java.util.List;

public interface ElementAnnotation extends Element {

	int typeIndex();

	int[] nameIndices();

	List<Element> values();

	void accept(AnnotationVisitor visitor);

	@Override
	ElementDescriptor<ElementAnnotation> descriptor();

	static ElementAnnotation create(int typeIndex, int[] nameIndices, List<Element> values) {
		return new ElementAnnotationImpl(typeIndex, nameIndices, values);
	}
}
