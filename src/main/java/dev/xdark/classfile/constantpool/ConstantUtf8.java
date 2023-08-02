package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantUtf8Impl;

public interface ConstantUtf8 extends Constant {

	String value();

	@Override
	Tag<ConstantUtf8> tag();

	static ConstantUtf8 create(String value) {
		return new ConstantUtf8Impl(value);
	}
}
