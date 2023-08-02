package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public interface ThrowInstruction extends Instruction {

	static ThrowInstruction create() {
		return BytecodeVisitor::athrow;
	}
}
