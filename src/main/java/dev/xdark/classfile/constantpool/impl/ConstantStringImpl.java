package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantString;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantStringImpl implements ConstantString, ConstantInternal {
	private final int utfIndex;

	public ConstantStringImpl(int utfIndex) {
		this.utfIndex = utfIndex;
	}

	@Override
	public int utfIndex() {
		return utfIndex;
	}

	@Override
	public Tag<ConstantString> tag() {
		return Tag.String;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantStringImpl that = (ConstantStringImpl) o;

		return utfIndex == that.utfIndex;
	}

	@Override
	public int hashCode() {
		return utfIndex;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(symtab.constantPool().get(utfIndex, Tag.Utf8).value());
	}

	public static Codec<ConstantString> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantStringImpl(input.readConstantPoolIndex()), ExactSkip.U2),
				(output, value) -> output.writeConstantPoolIndex(value.utfIndex())
		);
	}
}
