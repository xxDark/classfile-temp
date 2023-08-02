package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantDouble;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantDoubleImpl implements ConstantDouble, ConstantInternal {
	private final double value;

	public ConstantDoubleImpl(double value) {
		this.value = value;
	}

	@Override
	public double value() {
		return value;
	}

	@Override
	public Tag<ConstantDouble> tag() {
		return Tag.Double;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantDoubleImpl that = (ConstantDoubleImpl) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(value);
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(value);
	}

	public static Codec<ConstantDouble> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantDoubleImpl(input.readDouble()), ExactSkip.U2),
				(output, value) -> output.writeDouble(value.value())
		);
	}
}
