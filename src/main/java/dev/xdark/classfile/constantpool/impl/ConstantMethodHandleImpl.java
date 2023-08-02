package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantMethodHandle;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.Skip;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.impl.Decoder;
import dev.xdark.classfile.type.ReferenceKind;

public final class ConstantMethodHandleImpl implements ConstantMethodHandle, ConstantInternal {
	private final ReferenceKind referenceKind;
	private final int referenceIndex;

	public ConstantMethodHandleImpl(ReferenceKind referenceKind, int referenceIndex) {
		this.referenceKind = referenceKind;
		this.referenceIndex = referenceIndex;
	}

	@Override
	public ReferenceKind referenceKind() {
		return referenceKind;
	}

	@Override
	public int referenceIndex() {
		return referenceIndex;
	}

	@Override
	public Tag<ConstantMethodHandle> tag() {
		return Tag.MethodHandle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConstantMethodHandleImpl that = (ConstantMethodHandleImpl) o;

		if (referenceIndex != that.referenceIndex) return false;
		return referenceKind == that.referenceKind;
	}

	@Override
	public int hashCode() {
		return referenceKind.ordinal() | referenceIndex << 4;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		return LoadableConstant.create(Decoder.decodeMethodHandle(symtab.constantPool(), this));
	}

	public static Codec<ConstantMethodHandle> codec() {
		return Codec.wire(
				Input.wire(reader -> new ConstantMethodHandleImpl(ReferenceKind.byId(reader.readUnsignedByte()), reader.readConstantPoolIndex()), Skip.exact(3)),
				(writer, value) -> {
					writer.writeByte(value.referenceKind().id());
					writer.writeConstantPoolIndex(value.referenceIndex());
				}
		);
	}
}
