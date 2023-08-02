package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.bytecode.BytecodeAdapter;
import dev.xdark.classfile.bytecode.BytecodeVisitor;
import dev.xdark.classfile.bytecode.BytecodeVisitorSkeleton;
import dev.xdark.classfile.io.BinaryInput;

import java.io.IOException;
import java.io.UncheckedIOException;

public final class LabelMarkVisitor extends BytecodeAdapter {
	private static final BytecodeVisitor NOOP = new BytecodeVisitorSkeleton();
	private final InstructionPositionTracker positionTracker;
	private final LabelArray labelArray;

	public LabelMarkVisitor(InstructionPositionTracker positionTracker, LabelArray labelArray) {
		super(NOOP);
		this.positionTracker = positionTracker;
		this.labelArray = labelArray;
	}

	@Override
	public void ifeq(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void ifne(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void iflt(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void ifge(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void ifgt(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void ifle(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_icmpeq(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_icmpne(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_icmplt(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_icmpge(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_icmpgt(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_icmple(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_acmpeq(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void if_acmpne(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void goto_(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void jsr(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void table_switch(int defaultBranchOffset, int low, int high, BinaryInput offsets) {
		int pos = positionTracker.position();
		LabelArray la = labelArray;
		la.create(pos, defaultBranchOffset);
		try {
			while (offsets.isReadable(1L)) {
				la.create(pos, offsets.readInt());
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public void lookup_switch(int defaultBranchOffset, BinaryInput pairs) {
		int pos = positionTracker.position();
		LabelArray la = labelArray;
		la.create(pos, defaultBranchOffset);
		try {
			while (pairs.isReadable(1L)) {
				pairs.skipBytes(4L);
				la.create(pos, pairs.readInt());
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public void ifnull(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void ifnonnull(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void goto_w(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}

	@Override
	public void jsr_w(int offset) {
		labelArray.create(positionTracker.position(), offset);
	}
}
