package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.CodeElement;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.representation.model.CodeModel;

import java.util.ArrayList;
import java.util.List;

final class CodeModelImpl implements CodeModel {
	final List<CodeElement> elements = new ArrayList<>();
	final List<TryCatchBlock> tryCatchBlocks = new ArrayList<>();
	final List<UnrecognizedAttribute> unrecognizedAttributes = new ArrayList<>();
	int maxStack;
	int maxLocals;

	@Override
	public int maxStack() {
		return maxStack;
	}

	@Override
	public int maxLocals() {
		return maxLocals;
	}

	@Override
	public List<CodeElement> elements() {
		return elements;
	}

	@Override
	public List<TryCatchBlock> tryCatchBlocks() {
		return tryCatchBlocks;
	}

	@Override
	public List<UnrecognizedAttribute> unrecognizedAttributes() {
		return unrecognizedAttributes;
	}
}
