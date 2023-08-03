package dev.xdark.classfile.attribute.method.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.method.AnnotationDefaultAttribute;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class AnnotationDefaultAttributeImpl implements AnnotationDefaultAttribute {
	private final Element defaultValue;

	public AnnotationDefaultAttributeImpl(Element defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public Element defaultValue() {
		return defaultValue;
	}

	@Override
	public AttributeInfo<AnnotationDefaultAttribute> info() {
		return AttributeInfo.AnnotationDefault;
	}

	public static Codec<AnnotationDefaultAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new AnnotationDefaultAttributeImpl(Element.CODEC.read(reader));
				}, VariableSkip.U4.then(Element.CODEC)),
				(writer, value) -> {
					Element.CODEC.write(writer, value.defaultValue());
				}
		);
	}
}
