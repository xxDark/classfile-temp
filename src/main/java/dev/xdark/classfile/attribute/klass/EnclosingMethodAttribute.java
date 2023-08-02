package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface EnclosingMethodAttribute extends SpecAttribute {

	int classIndex();

	int methodIndex();

	@Override
	AttributeInfo<EnclosingMethodAttribute> info();
}
