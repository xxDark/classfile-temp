package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantStringImpl;

public interface ConstantString extends Constant {

	int utfIndex();

	@Override
	Tag<ConstantString> tag();

	static ConstantString create(int utfIndex) {
		return new ConstantStringImpl(utfIndex);
	}
}
