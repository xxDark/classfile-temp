package dev.xdark.classfile.attribute.field;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.field.impl.ConstantValueAttributeImpl;

public interface ConstantValueAttribute extends SpecAttribute {

	int constantIndex();

	@Override
	AttributeInfo<ConstantValueAttribute> info();

	static ConstantValueAttribute create(int constantIndex) {
		return new ConstantValueAttributeImpl(constantIndex);
	}
}
