package dev.xdark.classfile.attribute.shared;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.shared.impl.DeprecatedAttributeImpl;

public interface DeprecatedAttribute extends SpecAttribute {

	@Override
	AttributeInfo<DeprecatedAttribute> info();

	static DeprecatedAttribute create() {
		return DeprecatedAttributeImpl.INSTANCE;
	}
}
