package dev.xdark.classfile.constantpool;

public interface MutableConstantPool extends ConstantPool {

	int add(Constant constant);

	Constant set(int index, Constant constant);

	default void addAll(ConstantPool other) {
		for (Constant c : other) {
			add(c);
		}
	}
}
