package dev.xdark.classfile.io;

import java.io.IOException;

public interface Output<T> {

	void write(ClassWriter writer, T value) throws IOException;

	static <T> Output<T> none() {
		return (writer, value) -> {
		};
	}
}
