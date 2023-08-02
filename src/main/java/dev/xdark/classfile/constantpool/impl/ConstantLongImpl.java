package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantLong;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantLongImpl implements ConstantLong, ConstantInternal {
	private final long value;

	public ConstantLongImpl(long value) {
		this.value = value;
	}

	@Override
	public long value() {
		return value;
	}

	@Override
	public Tag<ConstantLong> tag() {
		return Tag.Long;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantLongImpl that = (ConstantLongImpl) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(value);
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(value);
	}

	public static Codec<ConstantLong> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantLongImpl(input.readLong()), ExactSkip.U8),
				(output, value) -> output.writeLong(value.value())
		);
	}
}
