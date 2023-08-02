package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.ReturnInstruction;
import dev.xdark.classfile.type.ClassType;

public final class ReturnInstructionImpl implements ReturnInstruction {
	private final ClassType type;

	public ReturnInstructionImpl(ClassType type) {
		this.type = type;
	}

	@Override
	public ClassType type() {
		return type;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.return_(type);
	}
}
