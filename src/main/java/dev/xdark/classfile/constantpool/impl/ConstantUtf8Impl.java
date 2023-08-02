package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantUtf8Impl implements ConstantUtf8, ConstantInternal {
	private final String value;

	public ConstantUtf8Impl(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public Tag<ConstantUtf8> tag() {
		return Tag.Utf8;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantUtf8Impl that = (ConstantUtf8Impl) o;

		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		throw new UnsupportedOperationException("Not loadable");
	}

	public static Codec<ConstantUtf8> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantUtf8Impl(input.readUtf()), VariableSkip.U2),
				(output, value) -> {
					output.writeUtf(value.value());
				}
		);
	}
}
