package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.instruction.impl.GotoInstructionImpl;

public interface GotoInstruction extends BranchInstruction, ImmediateJumpInstruction {

	static GotoInstruction create(Label label) {
		return new GotoInstructionImpl(label);
	}
}
