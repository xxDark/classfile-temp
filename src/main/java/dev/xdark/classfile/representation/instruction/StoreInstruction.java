package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.StoreInstructionImpl;
import dev.xdark.classfile.type.ClassType;

public interface StoreInstruction extends Instruction {

	int slot();

	ClassType type();

	static StoreInstruction create(int slot, ClassType type) {
		return new StoreInstructionImpl(slot, type);
	}
}
