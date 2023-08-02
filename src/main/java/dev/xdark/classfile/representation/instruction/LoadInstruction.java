package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.LoadInstructionImpl;
import dev.xdark.classfile.type.ClassType;

public interface LoadInstruction extends Instruction {

	int slot();

	ClassType type();

	static LoadInstruction create(int slot, ClassType type) {
		return new LoadInstructionImpl(slot, type);
	}
}
