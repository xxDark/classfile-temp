package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.frame.impl.SameFrameImpl;

public interface SameFrame extends StackMapFrame {

	@Override
	FrameType<SameFrame> type();

	static SameFrame create(int offsetDelta) {
		return SameFrameImpl.get(offsetDelta);
	}
}
