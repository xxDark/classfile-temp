package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantFloat;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantFloatImpl implements ConstantFloat, ConstantInternal {

	private final float value;

	public ConstantFloatImpl(float value) {
		this.value = value;
	}

	@Override
	public float value() {
		return value;
	}

	@Override
	public Tag<ConstantFloat> tag() {
		return Tag.Float;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantFloatImpl that = (ConstantFloatImpl) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Float.hashCode(value);
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(value);
	}

	public static Codec<ConstantFloat> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantFloatImpl(input.readFloat()), ExactSkip.U4),
				(output, value) -> output.writeFloat(value.value())
		);
	}
}
