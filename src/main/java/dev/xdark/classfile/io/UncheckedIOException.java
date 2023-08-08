package dev.xdark.classfile.io;

public final class UncheckedIOException extends RuntimeException {

	public UncheckedIOException() {
	}

	public UncheckedIOException(String message) {
		super(message);
	}

	public UncheckedIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public UncheckedIOException(Throwable cause) {
		super(cause);
	}
}
