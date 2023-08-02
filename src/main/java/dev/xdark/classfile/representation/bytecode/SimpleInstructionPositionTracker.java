package dev.xdark.classfile.representation.bytecode;

public final class SimpleInstructionPositionTracker implements InstructionPositionTracker {
	private int position;

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
		position = i;
	}
}
