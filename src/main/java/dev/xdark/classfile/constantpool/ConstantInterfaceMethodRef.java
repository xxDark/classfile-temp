package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantInterfaceMethodRefImpl;

public interface ConstantInterfaceMethodRef extends ConstantMemberRef {

	@Override
	Tag<ConstantInterfaceMethodRef> tag();

	static ConstantInterfaceMethodRef create(int classIndex, int nameAndTypeIndex) {
		return new ConstantInterfaceMethodRefImpl(classIndex, nameAndTypeIndex);
	}
}
