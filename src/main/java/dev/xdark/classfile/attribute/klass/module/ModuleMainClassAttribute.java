package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface ModuleMainClassAttribute extends SpecAttribute {

	int mainClassIndex();

	@Override
	AttributeInfo<ModuleMainClassAttribute> info();
}
