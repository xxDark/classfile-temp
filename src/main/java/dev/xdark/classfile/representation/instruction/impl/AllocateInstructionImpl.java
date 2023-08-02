package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.AllocateInstruction;
import dev.xdark.classfile.type.ObjectType;

public final class AllocateInstructionImpl implements AllocateInstruction {
	private final ObjectType type;

	public AllocateInstructionImpl(ObjectType type) {
		this.type = type;
	}

	@Override
	public ObjectType type() {
		return type;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.allocate(type);
	}
}
