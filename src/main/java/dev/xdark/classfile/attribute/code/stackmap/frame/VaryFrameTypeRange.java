package dev.xdark.classfile.attribute.code.stackmap.frame;

final class VaryFrameTypeRange implements FrameTypeRange {
	private final int from;
	private final int to;

	VaryFrameTypeRange(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int from() {
		return from;
	}

	@Override
	public int to() {
		return to;
	}
}
