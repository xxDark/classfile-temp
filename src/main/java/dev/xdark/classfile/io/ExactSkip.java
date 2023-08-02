package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

public final class ExactSkip implements Skip {
	public static final ExactSkip
			U2 = new ExactSkip(2),
			U4 = new ExactSkip(4),
			U8 = new ExactSkip(8);
	private final long toSkip;

	ExactSkip(long toSkip) {
		this.toSkip = toSkip;
	}

	@Override
	public void skip(ClassReader reader) throws IOException {
		reader.skipBytes(toSkip);
	}
}
