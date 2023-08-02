package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.instruction.impl.JumpSubroutineInstructionImpl;

public interface JumpSubroutineInstruction extends BranchInstruction, ImmediateJumpInstruction {

	static JumpSubroutineInstruction create(Label target) {
		return new JumpSubroutineInstructionImpl(target);
	}
}
