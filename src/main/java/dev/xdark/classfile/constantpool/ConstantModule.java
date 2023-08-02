package dev.xdark.classfile.constantpool;

public interface ConstantModule extends Constant {

	int nameIndex();

	@Override
	Tag<ConstantModule> tag();
}
