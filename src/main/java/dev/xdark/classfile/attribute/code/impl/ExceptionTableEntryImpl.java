package dev.xdark.classfile.attribute.code.impl;

import dev.xdark.classfile.attribute.code.ExceptionTableEntry;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ExceptionTableEntryImpl implements ExceptionTableEntry {
	private final int start, end, handler, typeIndex;

	public ExceptionTableEntryImpl(int start, int end, int handler, int typeIndex) {
		this.start = start;
		this.end = end;
		this.handler = handler;
		this.typeIndex = typeIndex;
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public int end() {
		return end;
	}

	@Override
	public int handler() {
		return handler;
	}

	@Override
	public int typeIndex() {
		return typeIndex;
	}

	public static Codec<ExceptionTableEntry> codec() {
		return Codec.wire(
				Input.wire(reader -> new ExceptionTableEntryImpl(reader.readUnsignedShort(), reader.readUnsignedShort(), reader.readUnsignedShort(), reader.readConstantPoolIndex()), ExactSkip.U8),
				(writer, value) -> {
					writer.writeShort(value.start());
					writer.writeShort(value.end());
					writer.writeShort(value.handler());
					writer.writeConstantPoolIndex(value.typeIndex());
				}
		);
	}
}
