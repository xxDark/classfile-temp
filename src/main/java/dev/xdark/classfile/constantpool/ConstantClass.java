package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantClassImpl;

public interface ConstantClass extends Constant {

	int nameIndex();

	@Override
	Tag<ConstantClass> tag();

	static ConstantClass create(int nameIndex) {
		return new ConstantClassImpl(nameIndex);
	}
}
