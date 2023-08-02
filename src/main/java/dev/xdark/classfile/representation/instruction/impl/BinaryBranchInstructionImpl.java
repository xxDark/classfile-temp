package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BinaryCondition;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.BinaryBranchInstruction;

import java.util.stream.Stream;

public final class BinaryBranchInstructionImpl implements BinaryBranchInstruction {
	private final BinaryCondition condition;
	private final Label target;

	public BinaryBranchInstructionImpl(BinaryCondition condition, Label target) {
		this.condition = condition;
		this.target = target;
	}

	@Override
	public BinaryCondition condition() {
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
		visitor.binaryBranch(condition, target);
	}
}
