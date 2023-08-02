package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.LookupSwitchInstruction;

import java.util.Arrays;
import java.util.List;

public final class LookupSwitchInstructionImpl extends SwitchInstructionsImpl implements LookupSwitchInstruction {
	private final int[] keys;

	public LookupSwitchInstructionImpl(Label defaultBranch, int[] keys, List<Label> cases) {
		super(defaultBranch, cases);
		this.keys = keys;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.lookupSwitch(defaultBranch(), keys, cases());
	}

	@Override
	public int[] keys() {
		return keys;
	}

	@Override
	public Label select(int key) {
		int idx = Arrays.binarySearch(keys, key);
		return idx < 0 ? defaultBranch() : cases().get(idx);
	}
}
