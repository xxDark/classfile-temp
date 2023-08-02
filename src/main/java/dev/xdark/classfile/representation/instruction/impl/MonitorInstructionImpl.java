package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.MonitorInstruction;

public final class MonitorInstructionImpl implements MonitorInstruction {
	public static final MonitorInstruction ENTER = new MonitorInstructionImpl(Operation.ENTER),
			EXIT = new MonitorInstructionImpl(Operation.EXIT);
	private final Operation operation;

	private MonitorInstructionImpl(Operation operation) {
		this.operation = operation;
	}

	@Override
	public Operation operation() {
		return operation;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		if (operation == Operation.ENTER) {
			visitor.monitorEnter();
		} else {
			visitor.monitorExit();
		}
	}
}
