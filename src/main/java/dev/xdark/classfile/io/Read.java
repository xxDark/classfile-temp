package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

public interface Read<T> {

	T read(ClassReader reader) throws IOException;

	static <T> Read<T> singleton(T value) {
		return reader -> value;
	}
}
