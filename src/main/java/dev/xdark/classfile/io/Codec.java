package dev.xdark.classfile.io;

public interface Codec<T> extends Input<T>, Output<T> {

	static <T> Codec<T> wire(Input<T> input, Output<T> output) {
		return new WiredCodec<>(input, output);
	}

	static <T> Codec<T> singleton(T value) {
		return wire(Input.singleton(value), Output.none());
	}
}
