package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.ElementDouble;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueDouble;

public final class ElementDoubleImpl extends ElementConstantImpl implements ElementDouble, ElementInternal {

	public ElementDoubleImpl(int constantIndex) {
		super(constantIndex);
	}

	@Override
	public ElementDescriptor<ElementDouble> descriptor() {
		return ElementDescriptor.DOUBLE;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		return ValueDouble.create(constantPool.get(constantIndex, Tag.Double).value());
	}

	public static Codec<ElementDouble> codec() {
		return makeCodec(ElementDoubleImpl::new);
	}
}
