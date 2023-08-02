package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantInterfaceMethodRef;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;

public final class ConstantInterfaceMethodRefImpl extends ConstantMemberRefImpl implements ConstantInterfaceMethodRef {

	public ConstantInterfaceMethodRefImpl(int classIndex, int nameAndTypeIndex) {
		super(classIndex, nameAndTypeIndex);
	}

	@Override
	public Tag<ConstantInterfaceMethodRef> tag() {
		return Tag.InterfaceMethodRef;
	}

	public static Codec<ConstantInterfaceMethodRef> codec() {
		return makeCodec(ConstantInterfaceMethodRefImpl::new);
	}
}
