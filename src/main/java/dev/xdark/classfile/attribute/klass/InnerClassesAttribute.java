package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

import java.util.List;

public interface InnerClassesAttribute extends SpecAttribute {

	List<InnerClass> innerClasses();

	@Override
	AttributeInfo<InnerClassesAttribute> info();
}
