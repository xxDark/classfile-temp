package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.ReturnFromSubroutineInstruction;

public final class ReturnFromSubroutineInstructionImpl implements ReturnFromSubroutineInstruction {
	private final int slot;

	public ReturnFromSubroutineInstructionImpl(int slot) {
		this.slot = slot;
	}

	@Override
	public int slot() {
		return slot;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.ret(slot);
	}
}
