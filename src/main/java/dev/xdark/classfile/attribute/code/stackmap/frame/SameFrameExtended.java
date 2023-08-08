package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.frame.impl.SameFrameExtendedImpl;

public interface SameFrameExtended extends StackMapFrame {

	@Override
	FrameType<SameFrameExtended> type();

	static SameFrameExtended create(int offsetDelta) {
		return new SameFrameExtendedImpl(offsetDelta);
	}
}
