package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantNameAndType;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantNameAndTypeImpl implements ConstantNameAndType, ConstantInternal {
	private final int nameIndex;
	private final int descriptorIndex;

	public ConstantNameAndTypeImpl(int nameIndex, int descriptorIndex) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public int descriptorIndex() {
		return descriptorIndex;
	}

	@Override
	public Tag<ConstantNameAndType> tag() {
		return Tag.NameAndType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantNameAndTypeImpl that = (ConstantNameAndTypeImpl) o;

		if (nameIndex != that.nameIndex) return false;
		return descriptorIndex == that.descriptorIndex;
	}

	@Override
	public int hashCode() {
		int result = nameIndex;
		result = 31 * result + descriptorIndex;
		return result;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		throw new UnsupportedOperationException("Not loadable");
	}

	public static Codec<ConstantNameAndType> codec() {
		return Codec.wire(
				Input.wire(reader -> new ConstantNameAndTypeImpl(reader.readConstantPoolIndex(), reader.readConstantPoolIndex()), ExactSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.nameIndex());
					writer.writeConstantPoolIndex(value.descriptorIndex());
				}
		);
	}
}
