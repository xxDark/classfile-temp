package dev.xdark.classfile.attribute.code.impl;

import dev.xdark.classfile.attribute.code.LineNumber;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class LineNumberImpl implements LineNumber {
	private final int start, lineNumber;

	public LineNumberImpl(int start, int lineNumber) {
		this.start = start;
		this.lineNumber = lineNumber;
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public int lineNumber() {
		return lineNumber;
	}

	public static Codec<LineNumber> codec() {
		return Codec.wire(
				Input.wire(reader -> new LineNumberImpl(reader.readUnsignedShort(), reader.readUnsignedShort()), ExactSkip.U4),
				(writer, value) -> {
					writer.writeShort(value.start());
					writer.writeShort(value.lineNumber());
				}
		);
	}
}
