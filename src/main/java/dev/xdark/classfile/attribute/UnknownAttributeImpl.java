package dev.xdark.classfile.attribute;

import dev.xdark.classfile.attribute.internal.PayloadAttribute;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.impl.ClassReaderImpl;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.UnrecognizedAttribute;

public final class UnknownAttributeImpl extends PayloadAttribute implements UnknownAttribute {

	public UnknownAttributeImpl(BinaryInput input) {
		super(input);
	}

	static Codec<UnknownAttribute> codec() {
		return makeCodec(UnknownAttributeImpl::new);
	}
}
