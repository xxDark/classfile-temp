package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.constantpool.MutableConstantPool;

public interface ValueInternal {

	Element denormalize(MutableConstantPool constantPool);
}
