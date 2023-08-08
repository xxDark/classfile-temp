package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

final class WiredCodec<T> implements Codec<T> {
	private final Input<T> input;
	private final Output<T> output;

	WiredCodec(Input<T> input, Output<T> output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public T read(ClassReader reader) {
		return this.input.read(reader);
	}

	@Override
	public void skip(ClassReader reader) {
		this.input.skip(reader);
	}

	@Override
	public void write(ClassWriter writer, T value) {
		this.output.write(writer, value);
	}
}
