package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.InstanceofInstructionImpl;
import dev.xdark.classfile.type.ObjectType;

public interface InstanceofInstruction extends Instruction {

	ObjectType type();

	static InstanceofInstruction create(ObjectType type) {
		return new InstanceofInstructionImpl(type);
	}
}
