package dev.xdark.classfile.attribute.method;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

import java.util.List;

public interface MethodParametersAttribute extends SpecAttribute {

	List<MethodParameter> parameters();

	@Override
	AttributeInfo<MethodParametersAttribute> info();
}
