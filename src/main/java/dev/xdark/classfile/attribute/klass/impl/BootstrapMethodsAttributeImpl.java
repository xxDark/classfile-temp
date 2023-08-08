package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.BootstrapMethod;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.ArrayList;
import java.util.List;

public final class BootstrapMethodsAttributeImpl implements BootstrapMethodsAttribute {

	private final List<BootstrapMethod> bootstrapMethods;

	public BootstrapMethodsAttributeImpl(List<BootstrapMethod> bootstrapMethods) {
		this.bootstrapMethods = bootstrapMethods;
	}

	@Override
	public List<BootstrapMethod> bootstrapMethods() {
		return bootstrapMethods;
	}

	@Override
	public AttributeInfo<BootstrapMethodsAttribute> info() {
		return AttributeInfo.BootstrapMethods;
	}

	public static Codec<BootstrapMethodsAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					long length = reader.readAttributeLength();
					AttributeHelper.checkAtLeast(length, 2L);
					int count = reader.readUnsignedShort();
					List<BootstrapMethod> bootstrapMethods = new ArrayList<>();
					while (count-- != 0) {
						bootstrapMethods.add(BootstrapMethod.CODEC.read(reader));
					}
					return new BootstrapMethodsAttributeImpl(bootstrapMethods);
				}, VariableSkip.U4),
				(writer, value) -> {
					List<BootstrapMethod> bootstrapMethods = value.bootstrapMethods();
					writer.writeShort(bootstrapMethods.size());
					for (BootstrapMethod bootstrapMethod : bootstrapMethods) {
						BootstrapMethod.CODEC.write(writer, bootstrapMethod);
					}
				}
		);
	}
}
