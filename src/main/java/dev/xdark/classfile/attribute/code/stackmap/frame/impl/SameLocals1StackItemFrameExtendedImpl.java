package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.attribute.code.stackmap.frame.SameLocals1StackItemFrameExtended;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class SameLocals1StackItemFrameExtendedImpl implements SameLocals1StackItemFrameExtended {
	private final int offsetDelta;
	private final VerificationTypeInfo item;

	private SameLocals1StackItemFrameExtendedImpl(int offsetDelta, VerificationTypeInfo item) {
		this.offsetDelta = offsetDelta;
		this.item = item;
	}

	@Override
	public VerificationTypeInfo item() {
		return item;
	}

	@Override
	public int offsetDelta() {
		return offsetDelta;
	}

	@Override
	public FrameType<SameLocals1StackItemFrameExtended> type() {
		return FrameType.SAME_LOCALS_1_STACK_ITEM_EXTENDED;
	}

	public static Codec<SameLocals1StackItemFrameExtended> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					reader.skipBytes(1L);
					int offsetDelta = reader.readUnsignedShort();
					VerificationTypeInfo info = VerificationTypeInfo.CODEC.read(reader);
					return new SameLocals1StackItemFrameExtendedImpl(offsetDelta, info);
				}, ExactSkip.U1.then(ExactSkip.U2).then(VerificationTypeInfo.CODEC)),
				(writer, value) -> {
					writer.writeByte(247);
					writer.writeShort(value.offsetDelta());
					VerificationTypeInfo.CODEC.write(writer, value.item());
				}
		);
	}
}
