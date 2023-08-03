package dev.xdark.classfile.attribute.method;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.shared.annotation.Element;

public interface AnnotationDefaultAttribute extends SpecAttribute {

	Element defaultValue();

	@Override
	AttributeInfo<AnnotationDefaultAttribute> info();
}
