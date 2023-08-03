package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public final class LabelImpl implements Label {
	private int position = -UNSET;
	private int lineNumber = UNSET;

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

	@Override
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.label(this);
	}

	public boolean isResolved() {
		return position != UNSET;
	}

	public int getOffset(long bytecodeOffset) {
		return position - ((int) bytecodeOffset);
	}
}
