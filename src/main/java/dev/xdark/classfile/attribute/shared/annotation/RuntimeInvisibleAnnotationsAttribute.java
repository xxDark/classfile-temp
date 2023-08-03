package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.AttributeInfo;

public interface RuntimeInvisibleAnnotationsAttribute extends RuntimeAnnotationsAttribute {

	@Override
	AttributeInfo<RuntimeInvisibleAnnotationsAttribute> info();
}
