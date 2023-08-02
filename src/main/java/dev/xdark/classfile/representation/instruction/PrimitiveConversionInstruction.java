package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.bytecode.PrimitiveConversion;
import dev.xdark.classfile.representation.instruction.impl.PrimitiveConversionInstructionImpl;
import dev.xdark.classfile.type.PrimitiveType;

public interface PrimitiveConversionInstruction extends Instruction {

	PrimitiveType from();

	PrimitiveType to();

	void accept(PrimitiveConversion conversion);

	static PrimitiveConversionInstruction create(PrimitiveType from, PrimitiveType to) {
		return new PrimitiveConversionInstructionImpl(from, to);
	}
}
