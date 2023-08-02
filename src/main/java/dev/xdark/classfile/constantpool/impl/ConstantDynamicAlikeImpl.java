package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantDynamicAlike;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

abstract class ConstantDynamicAlikeImpl implements ConstantDynamicAlike, ConstantInternal {
	private final int bootstrapMethodAttributeIndex;
	private final int nameAndTypeIndex;

	protected ConstantDynamicAlikeImpl(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
		this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	@Override
	public int bootstrapMethodAttributeIndex() {
		return bootstrapMethodAttributeIndex;
	}

	@Override
	public int nameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantDynamicAlikeImpl that = (ConstantDynamicAlikeImpl) o;

		if (bootstrapMethodAttributeIndex != that.bootstrapMethodAttributeIndex) return false;
		return nameAndTypeIndex == that.nameAndTypeIndex;
	}

	@Override
	public final int hashCode() {
		int result = bootstrapMethodAttributeIndex;
		result = 31 * result + nameAndTypeIndex;
		return result;
	}

	protected static <C extends ConstantDynamicAlike> Codec<C> makeCodec(ConstantCreator<C> creator) {
		return Codec.wire(
				Input.wire(reader -> creator.create(reader.readUnsignedShort(), reader.readConstantPoolIndex()), ExactSkip.U4),
				(writer, value) -> {
					writer.writeShort(value.bootstrapMethodAttributeIndex());
					writer.writeConstantPoolIndex(value.nameAndTypeIndex());
				}
		);
	}


	protected interface ConstantCreator<C extends ConstantDynamicAlike> {

		C create(int bootstrapMethodAttributeIndex, int nameAndTypeIndex);
	}
}
