package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.CompareInstruction;

import java.util.function.Consumer;

public final class CompareInstructionImpl implements CompareInstruction {
	public static final CompareInstruction LCMP = create(Mode.LCMP, BytecodeVisitor::lcmp);
	public static final CompareInstruction FCMPL = create(Mode.FCMPL, BytecodeVisitor::fcmpl);
	public static final CompareInstruction FCMPG = create(Mode.FCMPG, BytecodeVisitor::fcmpg);
	public static final CompareInstruction DCMPL = create(Mode.DCMPL, BytecodeVisitor::dcmpl);
	public static final CompareInstruction DCMPG = create(Mode.DCMPG, BytecodeVisitor::dcmpg);
	private static final CompareInstruction[] CACHE = {LCMP, FCMPL, FCMPG, DCMPL, DCMPG};
	private final Mode mode;
	private final Consumer<BytecodeVisitor> accept;

	private CompareInstructionImpl(Mode mode, Consumer<BytecodeVisitor> accept) {
		this.mode = mode;
		this.accept = accept;
	}

	@Override
	public Mode mode() {
		return mode;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		accept.accept(visitor);
	}

	public static CompareInstruction get(Mode mode) {
		return CACHE[mode.ordinal()];
	}

	private static CompareInstruction create(Mode mode, Consumer<BytecodeVisitor> accept) {
		return new CompareInstructionImpl(mode, accept);
	}
}
