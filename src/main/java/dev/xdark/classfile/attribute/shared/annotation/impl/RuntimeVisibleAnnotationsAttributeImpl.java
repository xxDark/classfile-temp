package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeInvisibleAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeVisibleAnnotationsAttribute;
import dev.xdark.classfile.io.Codec;

import java.util.List;

public final class RuntimeVisibleAnnotationsAttributeImpl extends RuntimeAnnotationsAttributeImpl implements RuntimeVisibleAnnotationsAttribute {

	public RuntimeVisibleAnnotationsAttributeImpl(List<ElementAnnotation> annotations) {
		super(annotations);
	}

	@Override
	public AttributeInfo<RuntimeVisibleAnnotationsAttribute> info() {
		return AttributeInfo.RuntimeVisibleAnnotations;
	}

	public static Codec<RuntimeVisibleAnnotationsAttribute> codec() {
		return makeCodec(RuntimeVisibleAnnotationsAttributeImpl::new);
	}
}
