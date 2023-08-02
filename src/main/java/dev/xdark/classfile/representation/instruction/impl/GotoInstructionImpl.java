package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.GotoInstruction;

import java.util.stream.Stream;

public final class GotoInstructionImpl implements GotoInstruction {
	private final Label target;

	public GotoInstructionImpl(Label target) {
		this.target = target;
	}

	@Override
	public Label target() {
		return target;
	}

	@Override
	public Stream<Label> targets() {
		return Stream.of(target);
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.goto_(target);
	}
}
