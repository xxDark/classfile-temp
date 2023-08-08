package dev.xdark.classfile.util;

import java.util.Iterator;

public interface MutableIterator<T> extends Iterator<T> {

	void set(T t);
}
