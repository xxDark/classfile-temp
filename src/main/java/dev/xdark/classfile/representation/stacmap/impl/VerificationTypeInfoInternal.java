package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.constantpool.MutableConstantPool;

public interface VerificationTypeInfoInternal {

	VerificationTypeInfo denormalize(MutableConstantPool constantPool);
}
