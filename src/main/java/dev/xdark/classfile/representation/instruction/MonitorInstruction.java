package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.MonitorInstructionImpl;

public interface MonitorInstruction extends Instruction {

	Operation operation();

	static MonitorInstruction enter() {
		return MonitorInstructionImpl.ENTER;
	}

	static MonitorInstruction exit() {
		return MonitorInstructionImpl.EXIT;
	}

	enum Operation {
		ENTER,
		EXIT,
	}
}
