package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.CheckCastInstructionImpl;
import dev.xdark.classfile.type.ObjectType;

public interface CheckCastInstruction extends Instruction {

	ObjectType type();

	static CheckCastInstruction create(ObjectType type) {
		return new CheckCastInstructionImpl(type);
	}
}
