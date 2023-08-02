package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.Label;

public final class SimpleLabelPlacementTracker implements LabelPlacementTracker {
	private final LabelArray labelArray;
	private final BytecodeVisitor visitor;
	private int index;
	private int position;

	public SimpleLabelPlacementTracker(LabelArray labelArray, BytecodeVisitor visitor) {
		this.labelArray = labelArray;
		this.visitor = visitor;
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public void accept(long value) {
		int i = (int) value;
		if (i < 0) {
			throw new IllegalArgumentException(String.format("Position too large %d", value));
		}
		int index = this.index;
		Label l = labelArray.get(i);
		if (l != null) {
			l.setPosition(index);
			visitor.label(l);
		}
		this.index = index + 1;
		position = i;
	}
}
