package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementChar;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueChar;

public final class ElementCharImpl extends ElementConstantImpl implements ElementChar, ElementInternal {

	public ElementCharImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementChar> descriptor() {
		return ElementDescriptor.CHAR;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueChar.create((char) constantPool.get(constantIndex, Tag.Integer).value());
	}

	public static Codec<ElementChar> codec() {
		return makeCodec(ElementCharImpl::new);
	}
}
