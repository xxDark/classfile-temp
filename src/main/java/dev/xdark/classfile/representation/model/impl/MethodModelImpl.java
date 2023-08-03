package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.model.CodeModel;
import dev.xdark.classfile.representation.model.MethodModel;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.type.MethodType;

import java.util.List;

final class MethodModelImpl extends MemberModelImpl implements MethodModel {
	CodeModel code;
	List<TryCatchBlock> tryCatchBlocks;

	MethodModelImpl(
			int accessFlags,
			String name,
			MethodType type
	) {
		super(accessFlags, name, type);
	}

	@Override
	public MethodType type() {
		return (MethodType) super.type();
	}

	@Override
	public CodeModel code() {
		return code;
	}

	@Override
	public List<TryCatchBlock> tryCatchBlocks() {
		return tryCatchBlocks;
	}
}
