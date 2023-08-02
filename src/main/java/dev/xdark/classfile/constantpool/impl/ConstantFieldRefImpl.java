package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantFieldRef;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;

public final class ConstantFieldRefImpl extends ConstantMemberRefImpl implements ConstantFieldRef {

	public ConstantFieldRefImpl(int classIndex, int nameAndTypeIndex) {
		super(classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag<ConstantFieldRef> tag() {
		return Tag.FieldRef;
	}

	public static Codec<ConstantFieldRef> codec() {
		return makeCodec(ConstantFieldRefImpl::new);
	}
}
