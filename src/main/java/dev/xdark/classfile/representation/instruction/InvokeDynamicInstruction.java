package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.instruction.impl.InvokeDynamicInstructionImpl;

public interface InvokeDynamicInstruction extends Instruction {

	InvokeDynamic info();

	static InvokeDynamicInstruction create(InvokeDynamic info) {
		return new InvokeDynamicInstructionImpl(info);
	}
}
