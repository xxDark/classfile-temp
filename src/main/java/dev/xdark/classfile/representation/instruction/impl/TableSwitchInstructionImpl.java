package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.TableSwitchInstruction;

import java.util.List;

public final class TableSwitchInstructionImpl extends SwitchInstructionsImpl implements TableSwitchInstruction {
	private final int low, high;

	public TableSwitchInstructionImpl(Label defaultBranch, int low, int high, List<Label> cases) {
		super(defaultBranch, cases);
		this.low = low;
		this.high = high;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.tableSwitch(defaultBranch(), low, high, cases());
	}

	@Override
	public Label select(int key) {
		int low = this.low;
		return (key -= low) > high - low ? defaultBranch() : cases().get(key);
	}

	@Override
	public int low() {
		return low;
	}

	@Override
	public int high() {
		return high;
	}
}
