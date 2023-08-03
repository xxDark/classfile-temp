package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementFloat;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueFloat;

public final class ElementFloatImpl extends ElementConstantImpl implements ElementFloat, ElementInternal {

	public ElementFloatImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementFloat> descriptor() {
		return ElementDescriptor.FLOAT;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueFloat.create(constantPool.get(constantIndex, Tag.Float).value());
	}

	public static Codec<ElementFloat> codec() {
		return makeCodec(ElementFloatImpl::new);
	}
}
