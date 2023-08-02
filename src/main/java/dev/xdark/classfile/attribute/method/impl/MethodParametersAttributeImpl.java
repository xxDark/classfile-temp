package dev.xdark.classfile.attribute.method.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.method.MethodParameter;
import dev.xdark.classfile.attribute.method.MethodParametersAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.List;

public final class MethodParametersAttributeImpl implements MethodParametersAttribute {
	private final List<MethodParameter> parameters;

	public MethodParametersAttributeImpl(List<MethodParameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public List<MethodParameter> parameters() {
		return parameters;
	}

	@Override
	public AttributeInfo<MethodParametersAttribute> info() {
		return AttributeInfo.MethodParameters;
	}

	public static Codec<MethodParametersAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new MethodParametersAttributeImpl(AttributeHelper.readList(reader, MethodParameter.CODEC));
				}, VariableSkip.U4),
				(writer, value) -> AttributeHelper.writeList(writer, value.parameters(), MethodParameter.CODEC)
		);
	}
}
