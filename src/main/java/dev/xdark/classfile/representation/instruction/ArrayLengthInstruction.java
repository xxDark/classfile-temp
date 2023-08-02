package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public interface ArrayLengthInstruction extends Instruction {

	static ArrayLengthInstruction create() {
		return BytecodeVisitor::arrayLength;
	}
}
