package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface NestMembersAttribute extends SpecAttribute {

	int[] classIndices();

	@Override
	AttributeInfo<NestMembersAttribute> info();
}
