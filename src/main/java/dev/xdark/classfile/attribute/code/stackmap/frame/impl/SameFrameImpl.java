package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.attribute.code.stackmap.frame.SameFrame;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class SameFrameImpl implements SameFrame {
	private static final SameFrame[] CACHE;
	private final int offsetDelta;

	private SameFrameImpl(int offsetDelta) {
		this.offsetDelta = offsetDelta;
	}

	@Override
	public int offsetDelta() {
		return offsetDelta;
	}

	@Override
	public FrameType<SameFrame> type() {
		return FrameType.SAME;
	}

	public static Codec<SameFrame> codec() {
		return Codec.wire(
				Input.wire(reader -> get(reader.readUnsignedByte()), ExactSkip.U1),
				// same_frame {
				//    u1 frame_type = SAME; /* 0-63 */
				// }
				(writer, value) -> writer.writeByte(value.offsetDelta())
		);
	}

	public static SameFrame get(int offsetDelta) {
		if (offsetDelta < 0 || offsetDelta > 64) {
			throw new IllegalArgumentException(String.format("Offset out of range %d", offsetDelta));
		}
		return CACHE[offsetDelta];
	}

	static {
		SameFrame[] cache = new SameFrame[64];
		for (int i = 0; i < 64; i++) {
			cache[i] = new SameFrameImpl(i);
		}
		CACHE = cache;
	}
}
