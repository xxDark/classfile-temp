package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.AllocateInstructionImpl;
import dev.xdark.classfile.type.ObjectType;

public interface AllocateInstruction extends Instruction {

	ObjectType type();

	static AllocateInstruction create(ObjectType type) {
		return new AllocateInstructionImpl(type);
	}
}
