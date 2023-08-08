package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.frame.impl.ChopFrameImpl;

public interface ChopFrame extends StackMapFrame {

	int absentLocals();

	@Override
	FrameType<ChopFrame> type();

	static ChopFrame create(int offsetDelta, int absentLocals) {
		if (absentLocals <= 0 || absentLocals > 3) {
			throw new IllegalArgumentException(String.format("Illegal amount of absent locals %d", absentLocals));
		}
		return new ChopFrameImpl(offsetDelta, absentLocals);
	}
}
