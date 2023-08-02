package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public interface PushNullInstruction extends Instruction {

	static PushNullInstruction create() {
		return BytecodeVisitor::pushNull;
	}
}
