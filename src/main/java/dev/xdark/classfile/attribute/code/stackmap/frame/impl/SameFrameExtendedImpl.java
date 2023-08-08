package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.attribute.code.stackmap.frame.SameFrameExtended;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class SameFrameExtendedImpl implements SameFrameExtended {
	private final int offsetDelta;

	public SameFrameExtendedImpl(int offsetDelta) {
		this.offsetDelta = offsetDelta;
	}

	@Override
	public int offsetDelta() {
		return offsetDelta;
	}

	@Override
	public FrameType<SameFrameExtended> type() {
		return FrameType.SAME_FRAME_EXTENDED;
	}

	public static Codec<SameFrameExtended> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					reader.skipBytes(1L);
					return new SameFrameExtendedImpl(reader.readUnsignedShort());
				}, ExactSkip.U3),
				(writer, value) -> {
					// same_frame_extended {
					//    u1 frame_type = SAME_FRAME_EXTENDED; /* 251 */
					//    u2 offset_delta;
					// }
					writer.writeByte(251);
					writer.writeShort(value.offsetDelta());
				}
		);
	}
}
