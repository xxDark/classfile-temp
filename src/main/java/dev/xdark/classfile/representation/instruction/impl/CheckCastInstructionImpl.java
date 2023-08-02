package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.CheckCastInstruction;
import dev.xdark.classfile.type.ObjectType;

public final class CheckCastInstructionImpl implements CheckCastInstruction {
	private final ObjectType type;

	public CheckCastInstructionImpl(ObjectType type) {
		this.type = type;
	}

	@Override
	public ObjectType type() {
		return type;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.checkCast(type);
	}
}
