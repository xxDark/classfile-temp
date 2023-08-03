package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.CodeElement;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.CodeElementReader;

final class CodeVisitorImpl implements CodeVisitor {
	private final CodeModelImpl codeModel;

	CodeVisitorImpl(CodeModelImpl codeModel) {
		this.codeModel = codeModel;
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return codeModel.unrecognizedAttributes::add;
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		CodeModelImpl cm = codeModel;
		cm.maxStack = maxStack;
		cm.maxLocals = maxLocals;
	}

	@Override
	public void visitTryCatchBlock(TryCatchBlock tryCatchBlock) {
		codeModel.tryCatchBlocks.add(tryCatchBlock);
	}

	@Override
	public BytecodeVisitor visitBytecode() {
		return new CodeElementReader() {
			@Override
			protected void accept(CodeElement element) {
				codeModel.elements.add(element);
			}
		};
	}
}
