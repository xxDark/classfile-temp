package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantIntImpl;

public interface ConstantInt extends Constant {

	int value();

	@Override
	Tag<ConstantInt> tag();

	static ConstantInt create(int value) {
		return new ConstantIntImpl(value);
	}
}
