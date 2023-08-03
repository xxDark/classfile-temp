package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationValue;

/**
 * Internal translation layer for faster speed.
 */
public interface ElementInternal {

	AnnotationValue normalise(ConstantPool constantPool);
}
