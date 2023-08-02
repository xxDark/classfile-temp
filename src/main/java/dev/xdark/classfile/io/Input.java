package dev.xdark.classfile.io;

public interface Input<T> extends Read<T>, Skip {

	static <T> Input<T> wire(Read<T> read, Skip skip) {
		return new WiredInput<>(read, skip);
	}

	static <T> Input<T> singleton(T value) {
		return wire(Read.singleton(value), Skip.zero());
	}
}
