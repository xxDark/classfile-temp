package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.StackInstructionImpl;

public interface StackInstruction extends Instruction {

	Operation operation();

	static StackInstruction pop() {
		return new StackInstructionImpl(Operation.POP);
	}

	static StackInstruction pop2() {
		return new StackInstructionImpl(Operation.POP2);
	}

	static StackInstruction dup() {
		return new StackInstructionImpl(Operation.DUP);
	}

	static StackInstruction dup_x1() {
		return new StackInstructionImpl(Operation.DUP_X1);
	}

	static StackInstruction dup_x2() {
		return new StackInstructionImpl(Operation.DUP_X2);
	}

	static StackInstruction dup2() {
		return new StackInstructionImpl(Operation.DUP2);
	}

	static StackInstruction dup2_x1() {
		return new StackInstructionImpl(Operation.DUP2_X1);
	}

	static StackInstruction dup2_x2() {
		return new StackInstructionImpl(Operation.DUP2_X2);
	}

	static StackInstruction swap() {
		return new StackInstructionImpl(Operation.SWAP);
	}

	enum Operation {
		POP,
		POP2,
		DUP,
		DUP_X1,
		DUP_X2,
		DUP2,
		DUP2_X1,
		DUP2_X2,
		SWAP,
	}
}
