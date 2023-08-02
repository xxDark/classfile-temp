package dev.xdark.classfile.attribute.code.stackmap.type.impl;

import dev.xdark.classfile.attribute.code.stackmap.type.ObjectVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.UninitializedThisVariableInfo;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeDescriptor;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ObjectVariableInfoImpl implements ObjectVariableInfo {
	private final int classIndex;

	public ObjectVariableInfoImpl(int classIndex) {
		this.classIndex = classIndex;
	}

	@Override
	public int classIndex() {
		return classIndex;
	}

	@Override
	public VerificationTypeDescriptor<ObjectVariableInfo> descriptor() {
		return VerificationTypeDescriptor.ITEM_Object;
	}

	public static Codec<ObjectVariableInfo> codec() {
		return Codec.wire(
				Input.wire(reader -> new ObjectVariableInfoImpl(reader.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.classIndex())
		);
	}
}
