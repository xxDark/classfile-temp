package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.klass.InnerClass;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class InnerClassImpl implements InnerClass {
	private final int innerClassInfoIndex;
	private final int outerClassInfoIndex;
	private final int innerNameIndex;
	private final int innerClassAccessFlags;

	InnerClassImpl(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags) {
		this.innerClassInfoIndex = innerClassInfoIndex;
		this.outerClassInfoIndex = outerClassInfoIndex;
		this.innerNameIndex = innerNameIndex;
		this.innerClassAccessFlags = innerClassAccessFlags;
	}

	@Override
	public int innerClassInfoIndex() {
		return innerClassInfoIndex;
	}

	@Override
	public int outerClassInfoIndex() {
		return outerClassInfoIndex;
	}

	@Override
	public int innerNameIndex() {
		return innerNameIndex;
	}

	@Override
	public int innerClassAccessFlags() {
		return innerClassAccessFlags;
	}

	public static Codec<InnerClass> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					return new InnerClassImpl(reader.readConstantPoolIndex(), reader.readConstantPoolIndex(), reader.readConstantPoolIndex(), reader.readAccessFlags());
				}, ExactSkip.U8),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.innerClassInfoIndex());
					writer.writeConstantPoolIndex(value.outerClassInfoIndex());
					writer.writeConstantPoolIndex(value.innerNameIndex());
					writer.writeAccessFlags(value.innerClassAccessFlags());
				}
		);
	}
}
