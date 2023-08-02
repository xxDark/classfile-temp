package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.UnaryCondition;
import dev.xdark.classfile.representation.instruction.impl.UnaryBranchInstructionImpl;

public interface UnaryBranchInstruction extends BranchInstruction {

	UnaryCondition condition();

	static UnaryBranchInstruction create(UnaryCondition condition, Label target) {
		return new UnaryBranchInstructionImpl(condition, target);
	}
}
