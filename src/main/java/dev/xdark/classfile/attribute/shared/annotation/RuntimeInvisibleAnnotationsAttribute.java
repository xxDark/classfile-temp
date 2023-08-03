package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.shared.annotation.impl.RuntimeInvisibleAnnotationsAttributeImpl;

import java.util.List;

public interface RuntimeInvisibleAnnotationsAttribute extends RuntimeAnnotationsAttribute {

	@Override
	AttributeInfo<RuntimeInvisibleAnnotationsAttribute> info();

	static RuntimeInvisibleAnnotationsAttribute create(List<ElementAnnotation> annotations) {
		return new RuntimeInvisibleAnnotationsAttributeImpl(annotations);
	}
}
