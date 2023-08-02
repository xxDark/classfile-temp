package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public final class LabelImpl implements Label {
	private int position = -1;

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.label(this);
	}

	public boolean isResolved() {
		return position != -1;
	}

	public int getOffset(long bytecodeOffset) {
		return position - ((int) bytecodeOffset);
	}
}
