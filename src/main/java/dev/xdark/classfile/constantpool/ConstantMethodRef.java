package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantMethodRefImpl;

public interface ConstantMethodRef extends ConstantMemberRef {

	@Override
	Tag<ConstantMethodRef> tag();

	static ConstantMethodRef create(int classIndex, int nameAndTypeIndex) {
		return new ConstantMethodRefImpl(classIndex, nameAndTypeIndex);
	}
}
