package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.instruction.impl.TableSwitchInstructionImpl;

import java.util.List;

public interface TableSwitchInstruction extends SwitchInstruction {

	int low();

	int high();

	static TableSwitchInstruction create(Label defaultBranch, int low, int high, List<Label> cases) {
		return new TableSwitchInstructionImpl(defaultBranch, low, high, cases);
	}
}
