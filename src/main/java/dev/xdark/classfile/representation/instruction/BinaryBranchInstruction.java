package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BinaryCondition;
import dev.xdark.classfile.representation.instruction.impl.BinaryBranchInstructionImpl;

public interface BinaryBranchInstruction extends BranchInstruction {

	BinaryCondition condition();

	static BinaryBranchInstruction create(BinaryCondition condition, Label target) {
		return new BinaryBranchInstructionImpl(condition, target);
	}
}
