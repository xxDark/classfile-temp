package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantMethodType;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.type.MethodType;

public final class ConstantMethodTypeImpl implements ConstantMethodType, ConstantInternal {
	private final int descriptorIndex;

	public ConstantMethodTypeImpl(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	@Override
	public int descriptorIndex() {
		return descriptorIndex;
	}

	@Override
	public Tag<ConstantMethodType> tag() {
		return Tag.MethodType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantMethodTypeImpl that = (ConstantMethodTypeImpl) o;

		return descriptorIndex == that.descriptorIndex;
	}

	@Override
	public int hashCode() {
		return descriptorIndex;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(MethodType.ofDescriptor(symtab.constantPool().get(descriptorIndex, Tag.Utf8).value()));
	}

	public static Codec<ConstantMethodType> codec() {
		return Codec.wire(
				Input.wire(reader -> new ConstantMethodTypeImpl(reader.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.descriptorIndex())
		);
	}
}
