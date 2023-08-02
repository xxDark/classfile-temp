package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.klass.impl.BootstrapMethodsAttributeImpl;

import java.util.List;

public interface BootstrapMethodsAttribute extends SpecAttribute {

	List<BootstrapMethod> bootstrapMethods();

	@Override
	AttributeInfo<BootstrapMethodsAttribute> info();

	static BootstrapMethodsAttribute create(List<BootstrapMethod> bootstrapMethods) {
		return new BootstrapMethodsAttributeImpl(bootstrapMethods);
	}
}
