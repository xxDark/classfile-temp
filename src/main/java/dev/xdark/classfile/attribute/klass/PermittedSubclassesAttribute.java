package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface PermittedSubclassesAttribute extends SpecAttribute {

	int[] classIndices();

	@Override
	AttributeInfo<PermittedSubclassesAttribute> info();
}
