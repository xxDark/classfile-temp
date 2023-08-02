package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantMethodTypeImpl;

public interface ConstantMethodType extends Constant {

	int descriptorIndex();

	@Override
	Tag<ConstantMethodType> tag();

	static ConstantMethodType create(int descriptorIndex) {
		return new ConstantMethodTypeImpl(descriptorIndex);
	}
}
