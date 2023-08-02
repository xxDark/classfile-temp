package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.MathOperation;
import dev.xdark.classfile.representation.instruction.MathInstruction;
import dev.xdark.classfile.type.PrimitiveType;

public final class MathInstructionImpl implements MathInstruction {
	private final PrimitiveType type;
	private final MathOperation operation;

	public MathInstructionImpl(PrimitiveType type, MathOperation operation) {
		this.type = type;
		this.operation = operation;
	}

	@Override
	public PrimitiveType type() {
		return type;
	}

	@Override
	public MathOperation operation() {
		return operation;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.mathOp(type, operation);
	}
}
