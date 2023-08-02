package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantDynamicImpl;

public interface ConstantDynamic extends ConstantDynamicAlike {

	@Override
	Tag<ConstantDynamic> tag();

	static ConstantDynamic create(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
		return new ConstantDynamicImpl(bootstrapMethodAttributeIndex, nameAndTypeIndex);
	}
}
