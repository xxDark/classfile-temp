package dev.xdark.classfile.attribute.shared.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.shared.DeprecatedAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.Output;
import dev.xdark.classfile.io.VariableSkip;

public final class DeprecatedAttributeImpl implements DeprecatedAttribute {
	public static final DeprecatedAttribute INSTANCE = new DeprecatedAttributeImpl();

	private DeprecatedAttributeImpl() {
	}

	@Override
	public AttributeInfo<DeprecatedAttribute> info() {
		return AttributeInfo.Deprecated;
	}

	public static Codec<DeprecatedAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 0L);
					return INSTANCE;
				}, VariableSkip.U4),
				Output.none()
		);
	}
}
