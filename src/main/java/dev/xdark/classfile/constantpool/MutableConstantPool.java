package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.util.MutableIterator;

public interface MutableConstantPool extends ConstantPool {

	@Override
	MutableIterator<Constant> iterator();

	int add(Constant constant);

	Constant set(int index, Constant constant);

	default void addAll(ConstantPool other) {
		for (Constant c : other) {
			add(c);
		}
	}
}
