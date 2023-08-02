package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantFieldRefImpl;

public interface ConstantFieldRef extends ConstantMemberRef {

	@Override
	Tag<ConstantFieldRef> tag();

	static ConstantFieldRef create(int classIndex, int nameAndTypeIndex) {
		return new ConstantFieldRefImpl(classIndex, nameAndTypeIndex);
	}
}
