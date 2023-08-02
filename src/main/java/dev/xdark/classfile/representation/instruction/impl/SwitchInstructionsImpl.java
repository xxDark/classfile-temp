package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.instruction.SwitchInstruction;

import java.util.List;
import java.util.stream.Stream;

abstract class SwitchInstructionsImpl implements SwitchInstruction {
	private final Label defaultBranch;
	private final List<Label> cases;

	SwitchInstructionsImpl(Label defaultBranch, List<Label> cases) {
		this.defaultBranch = defaultBranch;
		this.cases = cases;
	}

	@Override
	public final Label defaultBranch() {
		return defaultBranch;
	}

	@Override
	public final List<Label> cases() {
		return cases;
	}

	@Override
	public final Stream<Label> targets() {
		return Stream.concat(Stream.of(defaultBranch), cases.stream());
	}
}
