package dev.xdark.classfile.constantpool;

public interface ConstantMemberRef extends Constant {

	int classIndex();

	int nameAndTypeIndex();

	@Override
	Tag<? extends ConstantMemberRef> tag();
}
