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
	public void write(ClassWriter writer, T value) throws IOException {
		throw_(exceptionSupplier.get());
	}

	@Override
	public T read(ClassReader reader) throws IOException {
		return throw_(exceptionSupplier.get());
	}

	@Override
	public void skip(ClassReader reader) throws IOException {
		throw_(exceptionSupplier.get());
	}

	private static <X extends Exception, T> T throw_(Exception ex) throws X {
		throw (X) ex;
	}

	static <T> ThrowingCodec<T> io(Supplier<? extends IOException> exceptionSupplier) {
		return new ThrowingCodec<>(exceptionSupplier);
	}
}
