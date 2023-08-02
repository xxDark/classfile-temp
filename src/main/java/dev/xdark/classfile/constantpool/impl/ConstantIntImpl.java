package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantIntImpl implements ConstantInt, ConstantInternal {
	private final int value;

	public ConstantIntImpl(int value) {
		this.value = value;
	}

	@Override
	public int value() {
		return value;
	}

	@Override
	public Tag<ConstantInt> tag() {
		return Tag.Integer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantIntImpl that = (ConstantIntImpl) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(value);
	}

	public static Codec<ConstantInt> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantIntImpl(input.readInt()), ExactSkip.U4),
				(output, value) -> output.writeInt(value.value())
		);
	}
}
