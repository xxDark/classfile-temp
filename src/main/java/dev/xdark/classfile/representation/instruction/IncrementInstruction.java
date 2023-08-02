package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.IncrementInstructionImpl;

public interface IncrementInstruction extends Instruction {

	int slot();

	int value();

	static IncrementInstruction create(int slot, int value) {
		return new IncrementInstructionImpl(slot, value);
	}
}
