package dev.xdark.classfile.attribute.field;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface ConstantValueAttribute extends SpecAttribute {

	int constantIndex();

	@Override
	AttributeInfo<ConstantValueAttribute> info();
}
