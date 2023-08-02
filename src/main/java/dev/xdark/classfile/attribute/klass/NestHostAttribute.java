package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface NestHostAttribute extends SpecAttribute {

	int hostClassIndex();

	@Override
	AttributeInfo<NestHostAttribute> info();
}
