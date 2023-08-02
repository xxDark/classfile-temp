package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public interface NopInstruction extends Instruction {

	static NopInstruction create() {
		return BytecodeVisitor::nop;
	}
}
