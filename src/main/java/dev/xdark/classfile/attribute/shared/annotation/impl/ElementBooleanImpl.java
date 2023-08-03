package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementBoolean;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueBoolean;

public final class ElementBooleanImpl extends ElementConstantImpl implements ElementBoolean, ElementInternal {

	public ElementBooleanImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementBoolean> descriptor() {
		return ElementDescriptor.BOOLEAN;
	}

	public static Codec<ElementBoolean> codec() {
		return makeCodec(ElementBooleanImpl::new);
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueBoolean.create(constantPool.get(constantIndex, Tag.Integer).value() == 1);
	}
}
