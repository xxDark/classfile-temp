package dev.xdark.classfile.attribute.shared;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.shared.impl.SyntheticAttributeImpl;

public interface SyntheticAttribute extends SpecAttribute {

	@Override
	AttributeInfo<SyntheticAttribute> info();

	static SyntheticAttribute create() {
		return SyntheticAttributeImpl.INSTANCE;
	}
}
