package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.bytecode.MathOperation;
import dev.xdark.classfile.representation.instruction.impl.MathInstructionImpl;
import dev.xdark.classfile.type.PrimitiveType;

public interface MathInstruction extends Instruction {

	PrimitiveType type();

	MathOperation operation();

	static MathInstruction create(PrimitiveType type, MathOperation operation) {
		return new MathInstructionImpl(type, operation);
	}
}
