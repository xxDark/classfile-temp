package dev.xdark.classfile.attribute.code.stackmap.frame;

final class ExactFrameTypeRange implements FrameTypeRange {
	private final int tag;

	ExactFrameTypeRange(int tag) {
		this.tag = tag;
	}

	@Override
	public int from() {
		return tag;
	}

	@Override
	public int to() {
		return tag;
	}
}
