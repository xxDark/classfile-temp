package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

final class WiredInput<T> implements Input<T> {
	private final Read<T> read;
	private final Skip skip;

	WiredInput(Read<T> read, Skip skip) {
		this.read = read;
		this.skip = skip;
	}

	@Override
	public T read(ClassReader reader) {
		return read.read(reader);
	}

	@Override
	public void skip(ClassReader reader) {
		skip.skip(reader);
	}
}
