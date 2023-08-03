package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeInvisibleAnnotationsAttribute;
import dev.xdark.classfile.io.Codec;

import java.util.List;

public final class RuntimeInvisibleAnnotationsAttributeImpl extends RuntimeAnnotationsAttributeImpl implements RuntimeInvisibleAnnotationsAttribute {

	public RuntimeInvisibleAnnotationsAttributeImpl(List<ElementAnnotation> annotations) {
		super(annotations);
	}

	@Override
	public AttributeInfo<RuntimeInvisibleAnnotationsAttribute> info() {
		return AttributeInfo.RuntimeInvisibleAnnotations;
	}

	public static Codec<RuntimeInvisibleAnnotationsAttribute> codec() {
		return makeCodec(RuntimeInvisibleAnnotationsAttributeImpl::new);
	}
}
