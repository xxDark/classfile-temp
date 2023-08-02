package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueArrayImpl;

import java.util.List;

public interface ValueArray extends AnnotationValue {

	List<AnnotationValue> values();

	void accept(ValueArrayVisitor visitor);

	static ValueArray create(List<AnnotationValue> values) {
		return new ValueArrayImpl(values);
	}
}
