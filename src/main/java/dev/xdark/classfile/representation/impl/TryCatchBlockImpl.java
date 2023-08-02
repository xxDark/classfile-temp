package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.type.InstanceType;

public final class TryCatchBlockImpl implements TryCatchBlock {
	private final Label start, end, handler;
	private final InstanceType type;

	public TryCatchBlockImpl(Label start, Label end, Label handler, InstanceType type) {
		this.start = start;
		this.end = end;
		this.handler = handler;
		this.type = type;
	}

	@Override
	public Label start() {
		return start;
	}

	@Override
	public Label end() {
		return end;
	}

	@Override
	public Label handler() {
		return handler;
	}

	@Override
	public InstanceType type() {
		return type;
	}

	@Override
	public void accept(CodeVisitor visitor) {
		visitor.visitTryCatchBlock(this);
	}
}
