package dev.xdark.classfile.attribute.method;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.method.impl.MethodParametersAttributeImpl;

import java.util.List;

public interface MethodParametersAttribute extends SpecAttribute {

	List<MethodParameter> parameters();

	@Override
	AttributeInfo<MethodParametersAttribute> info();

	static MethodParametersAttribute create(List<MethodParameter> parameters) {
		return new MethodParametersAttributeImpl(parameters);
	}
}
