package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.IncrementInstruction;

public final class IncrementInstructionImpl implements IncrementInstruction {
	private final int slot, value;

	public IncrementInstructionImpl(int slot, int value) {
		this.slot = slot;
		this.value = value;
	}

	@Override
	public int slot() {
		return slot;
	}

	@Override
	public int value() {
		return value;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.increment(slot, value);
	}
}
