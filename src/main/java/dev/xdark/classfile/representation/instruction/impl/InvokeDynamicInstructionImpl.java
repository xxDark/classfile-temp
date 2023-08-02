package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.instruction.InvokeDynamicInstruction;

public final class InvokeDynamicInstructionImpl implements InvokeDynamicInstruction {
	private final InvokeDynamic info;

	public InvokeDynamicInstructionImpl(InvokeDynamic info) {
		this.info = info;
	}

	@Override
	public InvokeDynamic info() {
		return info;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.invokeDynamic(info);
	}
}
