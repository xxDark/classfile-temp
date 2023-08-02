package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.PayloadAttribute;
import dev.xdark.classfile.attribute.klass.SourceDebugExtensionAttribute;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.io.Codec;

public final class SourceDebugExtensionAttributeImpl extends PayloadAttribute implements SourceDebugExtensionAttribute {

	public SourceDebugExtensionAttributeImpl(BinaryInput input) {
		super(input);
	}

	@Override
	public AttributeInfo<SourceDebugExtensionAttribute> info() {
		return AttributeInfo.SourceDebugExtension;
	}

	public static Codec<SourceDebugExtensionAttribute> codec() {
		return makeCodec(SourceDebugExtensionAttributeImpl::new);
	}
}
