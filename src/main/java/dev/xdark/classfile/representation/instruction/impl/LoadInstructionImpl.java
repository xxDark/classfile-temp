package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.LoadInstruction;
import dev.xdark.classfile.representation.instruction.StoreInstruction;
import dev.xdark.classfile.type.ClassType;

public final class LoadInstructionImpl implements LoadInstruction {
	private final int slot;
	private final ClassType type;

	public LoadInstructionImpl(int slot, ClassType type) {
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
		visitor.load(type, slot);
	}
}
