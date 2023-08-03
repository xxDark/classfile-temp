package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.AttributeInfo;

public interface RuntimeVisibleAnnotationsAttribute extends RuntimeAnnotationsAttribute {

	@Override
	AttributeInfo<RuntimeVisibleAnnotationsAttribute> info();
}
