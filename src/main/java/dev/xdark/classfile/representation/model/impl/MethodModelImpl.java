package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
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

	@Override
	public void accept(MethodVisitor visitor) {
		super.accept(visitor);
		CodeModel code = this.code;
		if (code == null) {
			return;
		}
		CodeVisitor cv = visitor.visitCode();
		if (cv != null) {
			code.accept(cv);
		}
	}
}
