package dev.xdark.classfile.constantpool;

public interface ConstantDynamicAlike extends Constant {

	int bootstrapMethodAttributeIndex();

	int nameAndTypeIndex();

	@Override
	Tag<? extends ConstantDynamicAlike> tag();
}
