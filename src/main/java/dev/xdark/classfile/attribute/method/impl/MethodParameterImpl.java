package dev.xdark.classfile.attribute.method.impl;

import dev.xdark.classfile.attribute.method.MethodParameter;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class MethodParameterImpl implements MethodParameter {
	private final int nameIndex;
	private final int accessFlags;

	public MethodParameterImpl(int nameIndex, int accessFlags) {
		this.nameIndex = nameIndex;
		this.accessFlags = accessFlags;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public int accessFlags() {
		return accessFlags;
	}

	public static Codec<MethodParameter> codec() {
		return Codec.wire(
				Input.wire(reader -> new MethodParameterImpl(reader.readConstantPoolIndex(), reader.readAccessFlags()), ExactSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.nameIndex());
					writer.writeAccessFlags(value.accessFlags());
				}
		);
	}
}
