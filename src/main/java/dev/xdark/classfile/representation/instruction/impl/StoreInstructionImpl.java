package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.StoreInstruction;
import dev.xdark.classfile.type.ClassType;

public final class StoreInstructionImpl implements StoreInstruction {
	private final int slot;
	private final ClassType type;

	public StoreInstructionImpl(int slot, ClassType type) {
		this.slot = slot;
		this.type = type;
	}

	@Override
	public int slot() {
		return slot;
	}

	@Override
	public ClassType type() {
		return type;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.store(type, slot);
	}
}
