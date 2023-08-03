package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.shared.annotation.impl.RuntimeVisibleAnnotationsAttributeImpl;

import java.util.List;

public interface RuntimeVisibleAnnotationsAttribute extends RuntimeAnnotationsAttribute {

	@Override
	AttributeInfo<RuntimeVisibleAnnotationsAttribute> info();

	static RuntimeVisibleAnnotationsAttribute create(List<ElementAnnotation> annotations) {
		return new RuntimeVisibleAnnotationsAttributeImpl(annotations);
	}
}
