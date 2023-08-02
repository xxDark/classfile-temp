package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantPackage;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantPackageImpl implements ConstantPackage, ConstantInternal {
	private final int nameIndex;

	ConstantPackageImpl(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public Tag<ConstantPackage> tag() {
		return Tag.Package;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantPackageImpl that = (ConstantPackageImpl) o;

		return nameIndex == that.nameIndex;
	}

	@Override
	public int hashCode() {
		return nameIndex;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		throw new UnsupportedOperationException("Not loadable");
	}

	public static Codec<ConstantPackage> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantPackageImpl(input.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.nameIndex())
		);
	}
}
