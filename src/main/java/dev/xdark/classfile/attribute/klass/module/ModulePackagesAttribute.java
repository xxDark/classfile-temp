package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface ModulePackagesAttribute extends SpecAttribute {

	int[] packageIndices();

	@Override
	AttributeInfo<ModulePackagesAttribute> info();
}
