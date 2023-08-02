package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueTypeImpl;
import dev.xdark.classfile.type.Type;

public interface ValueType extends AnnotationValue {

	Type type();

	static ValueType create(Type type) {
		return new ValueTypeImpl(type);
	}
}
