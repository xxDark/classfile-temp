package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.CompareInstructionImpl;

public interface CompareInstruction extends Instruction {

	Mode mode();

	static CompareInstruction create(Mode mode) {
		return CompareInstructionImpl.get(mode);
	}

	static CompareInstruction lcmp() {
		return CompareInstructionImpl.LCMP;
	}

	static CompareInstruction fcmpl() {
		return CompareInstructionImpl.FCMPL;
	}

	static CompareInstruction fcmpg() {
		return CompareInstructionImpl.FCMPG;
	}

	static CompareInstruction dcmpl() {
		return CompareInstructionImpl.DCMPL;
	}

	static CompareInstruction dcmpg() {
		return CompareInstructionImpl.DCMPG;
	}

	enum Mode {
		LCMP,
		FCMPL,
		FCMPG,
		DCMPL,
		DCMPG,
	}
}
