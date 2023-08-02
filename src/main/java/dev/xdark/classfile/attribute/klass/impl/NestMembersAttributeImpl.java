package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.NestMembersAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class NestMembersAttributeImpl implements NestMembersAttribute {
	private final int[] classIndices;

	public NestMembersAttributeImpl(int[] classIndices) {
		this.classIndices = classIndices;
	}

	@Override
	public int[] classIndices() {
		return classIndices;
	}

	@Override
	public AttributeInfo<NestMembersAttribute> info() {
		return AttributeInfo.NestMembers;
	}

	public static Codec<NestMembersAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new NestMembersAttributeImpl(AttributeHelper.readUnsignedShorts(reader));
				}, VariableSkip.U4),
				(writer, value) -> {
					AttributeHelper.writeUnsignedShorts(writer, value.classIndices());
				}
		);
	}
}
