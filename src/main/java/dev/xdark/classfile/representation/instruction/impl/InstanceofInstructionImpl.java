package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.InstanceofInstruction;
import dev.xdark.classfile.type.ObjectType;

public final class InstanceofInstructionImpl implements InstanceofInstruction {
	private final ObjectType type;

	public InstanceofInstructionImpl(ObjectType type) {
		this.type = type;
	}

	@Override
	public ObjectType type() {
		return type;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.instanceOf(type);
	}
}
