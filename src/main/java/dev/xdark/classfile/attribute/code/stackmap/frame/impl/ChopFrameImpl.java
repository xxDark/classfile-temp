package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.ChopFrame;
import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ChopFrameImpl implements ChopFrame {
	private final int offsetDelta;
	private final int absentLocals;

	public ChopFrameImpl(int offsetDelta, int absentLocals) {
		this.offsetDelta = offsetDelta;
		this.absentLocals = absentLocals;
	}

	@Override
	public int absentLocals() {
		return absentLocals;
	}

	@Override
	public int offsetDelta() {
		return offsetDelta;
	}

	@Override
	public FrameType<ChopFrame> type() {
		return FrameType.CHOP;
	}

	public static Codec<ChopFrame> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					int type = reader.readUnsignedByte();
					int offsetDelta = reader.readUnsignedShort();
					int absentLocals = 251 - type;
					return new ChopFrameImpl(offsetDelta, absentLocals);
				}, ExactSkip.U3),
				(writer, value) -> {
					// chop_frame {
					//    u1 frame_type = CHOP; /* 248-250 */
					//    u2 offset_delta;
					// }
					writer.writeByte(248 + value.absentLocals());
					writer.writeShort(value.offsetDelta());
				}
		);
	}
}
