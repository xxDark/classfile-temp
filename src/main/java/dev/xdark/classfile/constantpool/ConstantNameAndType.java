package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantNameAndTypeImpl;

public interface ConstantNameAndType extends Constant {

	int nameIndex();

	int descriptorIndex();

	@Override
	Tag<ConstantNameAndType> tag();

	static ConstantNameAndType create(int nameIndex, int descriptorIndex) {
		return new ConstantNameAndTypeImpl(nameIndex, descriptorIndex);
	}
}
