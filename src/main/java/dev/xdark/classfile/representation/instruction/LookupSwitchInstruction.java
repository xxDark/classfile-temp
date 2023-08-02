package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.instruction.impl.LookupSwitchInstructionImpl;

import java.util.List;

public interface LookupSwitchInstruction extends SwitchInstruction {

	int[] keys();

	static LookupSwitchInstruction create(Label defaultBranch, int[] keys, List<Label> cases) {
		return new LookupSwitchInstructionImpl(defaultBranch, keys, cases);
	}
}
