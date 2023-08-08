package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.attribute.code.stackmap.frame.SameLocals1StackItemFrame;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class SameLocals1StackItemFrameImpl implements SameLocals1StackItemFrame {
	private final int offsetDelta;
	private final VerificationTypeInfo item;

	private SameLocals1StackItemFrameImpl(int offsetDelta, VerificationTypeInfo item) {
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
	public FrameType<SameLocals1StackItemFrame> type() {
		return FrameType.SAME_LOCALS_1_STACK_ITEM;
	}

	public static Codec<SameLocals1StackItemFrame> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					int offsetDelta = reader.readUnsignedByte() - 64;
					VerificationTypeInfo info = VerificationTypeInfo.CODEC.read(reader);
					return new SameLocals1StackItemFrameImpl(offsetDelta, info);
				}, ExactSkip.U1.then(VerificationTypeInfo.CODEC)),
				(writer, value) -> {
					// same_locals_1_stack_item_frame {
					//    u1 frame_type = SAME_LOCALS_1_STACK_ITEM; /* 64-127 */
					//    verification_type_info stack[1];
					// }
					writer.writeByte(64 + value.offsetDelta());
					VerificationTypeInfo.CODEC.write(writer, value.item());
				}
		);
	}
}
