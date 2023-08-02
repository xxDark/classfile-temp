package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

final class ZeroSkip implements Skip {
	static final Skip INSTANCE = new ZeroSkip();

	private ZeroSkip() {
	}

	@Override
	public void skip(ClassReader reader) throws IOException {
	}
}
