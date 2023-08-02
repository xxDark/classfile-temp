package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantMethodHandleImpl;
import dev.xdark.classfile.type.ReferenceKind;

public interface ConstantMethodHandle extends Constant {

	ReferenceKind referenceKind();

	int referenceIndex();

	@Override
	Tag<ConstantMethodHandle> tag();

	static ConstantMethodHandle create(ReferenceKind referenceKind, int referenceIndex) {
		return new ConstantMethodHandleImpl(referenceKind, referenceIndex);
	}
}
