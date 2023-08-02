package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantDoubleImpl;

public interface ConstantDouble extends ConstantWide {

	double value();

	@Override
	Tag<ConstantDouble> tag();

	static ConstantDouble create(double value) {
		return new ConstantDoubleImpl(value);
	}
}
