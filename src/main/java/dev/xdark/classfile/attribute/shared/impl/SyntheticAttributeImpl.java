package dev.xdark.classfile.attribute.shared.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.shared.SyntheticAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.Output;
import dev.xdark.classfile.io.VariableSkip;

public final class SyntheticAttributeImpl implements SyntheticAttribute {
	public static final SyntheticAttribute INSTANCE = new SyntheticAttributeImpl();

	private SyntheticAttributeImpl() {
	}

	@Override
	public AttributeInfo<SyntheticAttribute> info() {
		return AttributeInfo.Synthetic;
	}

	public static Codec<SyntheticAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 0L);
					return INSTANCE;
				}, VariableSkip.U4),
				Output.none()
		);
	}
}
