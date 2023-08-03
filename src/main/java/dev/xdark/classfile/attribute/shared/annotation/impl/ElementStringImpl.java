package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementString;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueString;

public final class ElementStringImpl extends ElementConstantImpl implements ElementString, ElementInternal {

	public ElementStringImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementString> descriptor() {
		return ElementDescriptor.STRING;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueString.create(constantPool.get(constantIndex, Tag.Utf8).value());
	}

	public static Codec<ElementString> codec() {
		return makeCodec(ElementStringImpl::new);
	}
}
