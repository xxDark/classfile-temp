package dev.xdark.classfile;

import java.io.IOException;

public final class BadClassFileFormatException extends IOException {

	public BadClassFileFormatException() {
	}

	public BadClassFileFormatException(String message) {
		super(message);
	}

	public BadClassFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadClassFileFormatException(Throwable cause) {
		super(cause);
	}
}
