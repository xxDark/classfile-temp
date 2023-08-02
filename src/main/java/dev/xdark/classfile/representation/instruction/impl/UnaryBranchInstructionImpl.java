package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.UnaryCondition;
import dev.xdark.classfile.representation.instruction.UnaryBranchInstruction;

import java.util.stream.Stream;

public final class UnaryBranchInstructionImpl implements UnaryBranchInstruction {
	private final UnaryCondition condition;
	private final Label target;

	public UnaryBranchInstructionImpl(UnaryCondition condition, Label target) {
		this.condition = condition;
		this.target = target;
	}

	@Override
	public UnaryCondition condition() {
		return condition;
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
		visitor.unaryBranch(condition, target);
	}
}
