package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.type.MethodType;

import java.util.List;

public interface MethodModel extends MemberModel {

	@Override
	MethodType type();

	CodeModel code();

	List<TryCatchBlock> tryCatchBlocks();
}
