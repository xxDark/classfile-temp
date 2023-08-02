package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantMemberRef;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

abstract class ConstantMemberRefImpl implements ConstantMemberRef, ConstantInternal {

	private final int classIndex;
	private final int nameAndTypeIndex;

	protected ConstantMemberRefImpl(int classIndex, int nameAndTypeIndex) {
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	@Override
	public final int classIndex() {
		return classIndex;
	}

	@Override
	public final int nameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantMemberRefImpl that = (ConstantMemberRefImpl) o;

		if (classIndex != that.classIndex) return false;
		return nameAndTypeIndex == that.nameAndTypeIndex;
	}

	@Override
	public final int hashCode() {
		int result = classIndex;
		result = 31 * result + nameAndTypeIndex;
		return result;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		throw new UnsupportedOperationException("Not loadable");
	}

	protected static <C extends ConstantMemberRef> Codec<C> makeCodec(ConstantCreator<C> creator) {
		return Codec.wire(
				Input.wire(input -> creator.create(input.readConstantPoolIndex(), input.readConstantPoolIndex()), ExactSkip.U4),
				(output, value) -> {
					output.writeConstantPoolIndex(value.classIndex());
					output.writeConstantPoolIndex(value.nameAndTypeIndex());
				}
		);
	}

	protected interface ConstantCreator<C extends ConstantMemberRef> {

		C create(int classIndex, int nameAndTypeIndex);
	}
}
