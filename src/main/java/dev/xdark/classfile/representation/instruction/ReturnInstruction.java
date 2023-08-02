package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.ReturnInstructionImpl;
import dev.xdark.classfile.type.ClassType;

public interface ReturnInstruction extends Instruction {

	ClassType type();

	static ReturnInstruction create(ClassType type) {
		return new ReturnInstructionImpl(type);
	}
}
