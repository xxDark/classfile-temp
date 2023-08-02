package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.StackInstruction;

public final class StackInstructionImpl implements StackInstruction {
	private final Operation operation;

	public StackInstructionImpl(Operation operation) {
		this.operation = operation;
	}

	@Override
	public Operation operation() {
		return operation;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		switch (operation) {
			case POP:
				visitor.pop();
				break;
			case POP2:
				visitor.pop2();
				break;
			case DUP:
				visitor.dup();
				break;
			case DUP_X1:
				visitor.dup_x1();
				break;
			case DUP_X2:
				visitor.dup_x2();
				break;
			case DUP2:
				visitor.dup2();
				break;
			case DUP2_X1:
				visitor.dup2_x1();
				break;
			case DUP2_X2:
				visitor.dup2_x2();
				break;
			case SWAP:
				visitor.swap();
		}
	}
}
