package dev.xdark.classfile.attribute.method;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.method.impl.ExceptionsAttributeImpl;

public interface ExceptionsAttribute extends SpecAttribute {

	int[] exceptionIndices();

	@Override
	AttributeInfo<ExceptionsAttribute> info();

	static ExceptionsAttribute create(int[] exceptionIndices) {
		return new ExceptionsAttributeImpl(exceptionIndices);
	}
}
