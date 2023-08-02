package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.JumpSubroutineInstruction;

import java.util.stream.Stream;

public final class JumpSubroutineInstructionImpl implements JumpSubroutineInstruction {
	private final Label target;

	public JumpSubroutineInstructionImpl(Label target) {
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
		visitor.jsr(target);
	}
}
