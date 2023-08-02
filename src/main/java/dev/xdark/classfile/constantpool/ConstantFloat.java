package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantFloatImpl;

public interface ConstantFloat extends Constant {

	float value();

	@Override
	Tag<ConstantFloat> tag();

	static ConstantFloat create(float value) {
		return new ConstantFloatImpl(value);
	}
}
