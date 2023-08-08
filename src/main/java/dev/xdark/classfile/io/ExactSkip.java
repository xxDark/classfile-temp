package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

public final class ExactSkip implements Skip {
	public static final ExactSkip
			U1 = new ExactSkip(1),
			U2 = new ExactSkip(2),
			U3 = new ExactSkip(3),
			U4 = new ExactSkip(4),
			U8 = new ExactSkip(8);
	private final long toSkip;

	ExactSkip(long toSkip) {
		this.toSkip = toSkip;
	}

	@Override
	public void skip(ClassReader reader) {
		reader.skipBytes(toSkip);
	}
}
