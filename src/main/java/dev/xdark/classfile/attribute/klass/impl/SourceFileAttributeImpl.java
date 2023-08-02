package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.klass.SourceFileAttribute;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;
import dev.xdark.classfile.attribute.AttributeInfo;

public final class SourceFileAttributeImpl implements SourceFileAttribute {
	private final int sourceFileIndex;

	SourceFileAttributeImpl(int sourceFileIndex) {
		this.sourceFileIndex = sourceFileIndex;
	}

	@Override
	public int sourceFileIndex() {
		return sourceFileIndex;
	}

	@Override
	public AttributeInfo<SourceFileAttribute> info() {
		return AttributeInfo.SourceFile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SourceFileAttributeImpl that = (SourceFileAttributeImpl) o;

		return sourceFileIndex == that.sourceFileIndex;
	}

	@Override
	public int hashCode() {
		return sourceFileIndex;
	}

	public static Codec<SourceFileAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 2);
					return new SourceFileAttributeImpl(reader.readConstantPoolIndex());
				}, VariableSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.sourceFileIndex());
				}
		);
	}
}
