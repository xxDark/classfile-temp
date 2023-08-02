package dev.xdark.classfile.representation.annotation;

import dev.xdark.classfile.representation.annotation.impl.ValueEnumImpl;
import dev.xdark.classfile.type.InstanceType;

public interface ValueEnum extends AnnotationValue {

	InstanceType type();

	String name();

	static ValueEnum create(InstanceType type, String name) {
		return new ValueEnumImpl(type, name);
	}
}
