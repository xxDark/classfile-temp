package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantClass;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.type.ObjectType;

public final class ConstantClassImpl implements ConstantClass, ConstantInternal {
	private final int nameIndex;

	public ConstantClassImpl(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public Tag<ConstantClass> tag() {
		return Tag.Class;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantClassImpl that = (ConstantClassImpl) o;

		return nameIndex == that.nameIndex;
	}

	@Override
	public int hashCode() {
		return nameIndex;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(ObjectType.ofInternalName(symtab.constantPool().get(nameIndex, Tag.Utf8).value()));
	}

	public static Codec<ConstantClass> codec() {
		return Codec.wire(
				Input.wire(input -> new ConstantClassImpl(input.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.nameIndex())
		);
	}
}
