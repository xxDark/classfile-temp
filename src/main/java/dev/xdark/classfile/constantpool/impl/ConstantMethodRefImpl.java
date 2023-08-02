package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantMethodRef;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;

public final class ConstantMethodRefImpl extends ConstantMemberRefImpl implements ConstantMethodRef {

	public ConstantMethodRefImpl(int classIndex, int nameAndTypeIndex) {
		super(classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag<ConstantMethodRef> tag() {
		return Tag.MethodRef;
	}

	public static Codec<ConstantMethodRef> CODEC() {
		return makeCodec(ConstantMethodRefImpl::new);
	}
}
