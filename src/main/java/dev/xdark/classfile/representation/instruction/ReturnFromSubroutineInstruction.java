package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.ReturnFromSubroutineInstructionImpl;

public interface ReturnFromSubroutineInstruction extends Instruction {

	int slot();

	static ReturnFromSubroutineInstruction create(int slot) {
		return new ReturnFromSubroutineInstructionImpl(slot);
	}
}
