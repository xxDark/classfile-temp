package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantInvokeDynamicImpl;

public interface ConstantInvokeDynamic extends ConstantDynamicAlike {

	@Override
	Tag<ConstantInvokeDynamic> tag();

	static ConstantInvokeDynamic create(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
		return new ConstantInvokeDynamicImpl(bootstrapMethodAttributeIndex, nameAndTypeIndex);
	}
}
