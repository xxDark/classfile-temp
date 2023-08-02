package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.constantpool.ConstantDynamic;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class ConstantDynamicImpl extends ConstantDynamicAlikeImpl implements ConstantDynamic {

	public ConstantDynamicImpl(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
		super(bootstrapMethodAttributeIndex, nameAndTypeIndex);
	}

	@Override
	public Tag<ConstantDynamic> tag() {
		return Tag.ConstantDynamic;
	}

	@Override
	public LoadableConstant asLoadable(SymbolTable symtab) {
		throw new UnsupportedOperationException("Should've been translated at call site");
	}

	public static Codec<ConstantDynamic> codec() {
		return makeCodec(ConstantDynamicImpl::new);
	}
}
