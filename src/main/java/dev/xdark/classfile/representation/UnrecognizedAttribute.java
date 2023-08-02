package dev.xdark.classfile.representation;

import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.representation.impl.UnrecognizedAttributeImpl;

public interface UnrecognizedAttribute extends UnknownAttribute {

	String name();

	static UnrecognizedAttribute create(String name, BinaryInput payload) {
		return new UnrecognizedAttributeImpl(name, payload);
	}
}
