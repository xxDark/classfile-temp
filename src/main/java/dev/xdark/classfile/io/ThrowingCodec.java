package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;
import java.util.function.Supplier;

final class ThrowingCodec<T> implements Codec<T> {

	private final Supplier<? extends Exception> exceptionSupplier;

	private ThrowingCodec(Supplier<? extends Exception> exceptionSupplier) {
		this.exceptionSupplier = exceptionSupplier;
	}

	@Override
	public void write(ClassWriter writer, T value) {
		throw_(exceptionSupplier.get());
	}

	@Override
	public T read(ClassReader reader) {
		return throw_(exceptionSupplier.get());
	}

	@Override
	public void skip(ClassReader reader) {
		throw_(exceptionSupplier.get());
	}

	private static <X extends Exception, T> T throw_(Exception ex) throws X {
		throw (X) ex;
	}

	static <T> ThrowingCodec<T> io(Supplier<? extends IOException> exceptionSupplier) {
		return new ThrowingCodec<>(exceptionSupplier);
	}
}
