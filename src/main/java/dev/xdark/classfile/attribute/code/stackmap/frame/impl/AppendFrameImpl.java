package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.AppendFrame;
import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

import java.util.List;

public final class AppendFrameImpl implements AppendFrame {
	private final int offsetDelta;
	private final List<VerificationTypeInfo> locals;

	public AppendFrameImpl(int offsetDelta, List<VerificationTypeInfo> locals) {
		this.offsetDelta = offsetDelta;
		this.locals = locals;
	}

	@Override
	public List<VerificationTypeInfo> locals() {
		return locals;
	}

	@Override
	public int offsetDelta() {
		return offsetDelta;
	}

	@Override
	public FrameType<AppendFrame> type() {
		return FrameType.APPEND;
	}

	public static Codec<AppendFrame> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					int type = reader.readUnsignedByte();
					int offsetDelta = reader.readUnsignedShort();
					int numLocals = type - 251;
					List<VerificationTypeInfo> locals = AttributeHelper.readList(reader, numLocals, VerificationTypeInfo.CODEC);
					return new AppendFrameImpl(offsetDelta, locals);
				}, reader -> {
					int type = reader.readUnsignedByte();
					reader.skipBytes(2L);
					int numLocals = type - 251;
					while (numLocals-- != 0) {
						VerificationTypeInfo.CODEC.skip(reader);
					}
				}),
				(writer, value) -> {
					// append_frame {
					//    u1 frame_type = APPEND; /* 252-254 */
					//    u2 offset_delta;
					//    verification_type_info locals[frame_type - 251];
					// }
					List<VerificationTypeInfo> locals = value.locals();
					writer.writeByte(252 + locals.size());
					int offsetDelta = value.offsetDelta();
					writer.writeShort(offsetDelta);
					for (VerificationTypeInfo info : locals) {
						VerificationTypeInfo.CODEC.write(writer, info);
					}
				}
		);
	}
}
