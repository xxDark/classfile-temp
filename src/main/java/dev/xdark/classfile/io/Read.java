package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

public interface Read<T> {

	T read(ClassReader reader);

	static <T> Read<T> singleton(T value) {
		return reader -> value;
	}
}
