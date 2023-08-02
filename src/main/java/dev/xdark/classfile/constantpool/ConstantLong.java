package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantLongImpl;

public interface ConstantLong extends ConstantWide {

	long value();

	@Override
	Tag<ConstantLong> tag();

	static ConstantLong create(long value) {
		return new ConstantLongImpl(value);
	}
}
