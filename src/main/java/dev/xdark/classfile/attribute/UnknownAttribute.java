package dev.xdark.classfile.attribute;

import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.io.Codec;

import java.io.IOException;

public interface UnknownAttribute extends Attribute {
	Codec<UnknownAttribute> CODEC = UnknownAttributeImpl.codec();

	BinaryInput payload();

	static UnknownAttribute create(BinaryInput payload) {
		return new UnknownAttributeImpl(payload);
	}
}
