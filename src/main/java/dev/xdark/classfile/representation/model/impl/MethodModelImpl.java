package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.model.CodeModel;
import dev.xdark.classfile.representation.model.MethodModel;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.Type;

import java.util.List;

final class MethodModelImpl extends MemberModelImpl implements MethodModel {
	private final CodeModel code;
	private final List<TryCatchBlock> tryCatchBlocks;

	MethodModelImpl(int accessFlags, String name, Type type, String signature, CodeModel code, List<TryCatchBlock> tryCatchBlocks, List<UnrecognizedAttribute> unrecognizedAttributes) {
		super(accessFlags, name, type, signature, unrecognizedAttributes);
		this.code = code;
		this.tryCatchBlocks = tryCatchBlocks;
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
