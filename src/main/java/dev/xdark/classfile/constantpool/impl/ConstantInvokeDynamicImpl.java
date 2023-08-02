package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantInvokeDynamic;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantInvokeDynamicImpl extends ConstantDynamicAlikeImpl implements ConstantInvokeDynamic {

	public ConstantInvokeDynamicImpl(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
		super(bootstrapMethodAttributeIndex, nameAndTypeIndex);
	}

	@Override
	public Tag<ConstantInvokeDynamic> tag() {
		return Tag.InvokeDynamic;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		throw new UnsupportedOperationException("Not loadable");
	}

	public static Codec<ConstantInvokeDynamic> codec() {
		return makeCodec(ConstantInvokeDynamicImpl::new);
	}
}
