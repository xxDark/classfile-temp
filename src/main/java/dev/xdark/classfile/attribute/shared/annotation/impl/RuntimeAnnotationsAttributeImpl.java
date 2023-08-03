package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeAnnotationsAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.List;
import java.util.function.Function;

abstract class RuntimeAnnotationsAttributeImpl implements RuntimeAnnotationsAttribute {
	private final List<ElementAnnotation> annotations;

	protected RuntimeAnnotationsAttributeImpl(List<ElementAnnotation> annotations) {
		this.annotations = annotations;
	}

	@Override
	public final List<ElementAnnotation> annotations() {
		return annotations;
	}

	protected static <A extends RuntimeAnnotationsAttribute> Codec<A> makeCodec(Function<List<ElementAnnotation>, ? extends A> fn) {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return fn.apply(AttributeHelper.readList(reader, ElementDescriptor.ANNOTATION.codec()));
				}, VariableSkip.U4),
				(writer, value) -> {
					AttributeHelper.writeList(writer, value.annotations(), ElementDescriptor.ANNOTATION.codec());
				}
		);
	}
}
