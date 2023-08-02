package dev.xdark.classfile.constantpool;

public interface ConstantPackage extends Constant {

	int nameIndex();

	@Override
	Tag<ConstantPackage> tag();
}
