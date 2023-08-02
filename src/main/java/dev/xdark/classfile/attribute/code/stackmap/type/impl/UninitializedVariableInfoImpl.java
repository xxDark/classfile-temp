package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.ObjectVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.UninitializedVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class UninitializedVariableInfoImpl implements UninitializedVariableInfo {
	private final int offset;

	public UninitializedVariableInfoImpl(int offset) {
		this.offset = offset;
	}

	@Override
	public int offset() {
		return offset;
	}

	@Override
	public VerificationTypeDescriptor<UninitializedVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Uninitialized;
	}

	public static Codec<UninitializedVariableInfo> codec() {
		return Codec.wire(
				Input.wire(reader -> new UninitializedVariableInfoImpl(reader.readUnsignedShort()), ExactSkip.U2),
				(writer, value) -> writer.writeShort(value.offset())
		);
	}
}
