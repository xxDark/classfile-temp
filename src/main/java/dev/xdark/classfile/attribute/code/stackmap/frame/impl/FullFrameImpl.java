package dev.xdark.classfile.attribute.code.stackmap.frame.impl;

import dev.xdark.classfile.attribute.code.stackmap.frame.FrameType;
import dev.xdark.classfile.attribute.code.stackmap.frame.FullFrame;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

import java.util.List;

public final class FullFrameImpl implements FullFrame {
	private final int offsetDelta;
	private final List<VerificationTypeInfo> locals;
	private final List<VerificationTypeInfo> stack;

	public FullFrameImpl(int offsetDelta, List<VerificationTypeInfo> locals, List<VerificationTypeInfo> stack) {
		this.offsetDelta = offsetDelta;
		this.locals = locals;
		this.stack = stack;
	}

	@Override
	public List<VerificationTypeInfo> locals() {
		return locals;
	}

	@Override
	public List<VerificationTypeInfo> stack() {
		return stack;
	}

	@Override
	public int offsetDelta() {
		return offsetDelta;
	}

	@Override
	public FrameType<FullFrame> type() {
		return FrameType.FULL_FRAME;
	}

	public static Codec<FullFrame> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					reader.skipBytes(1L);
					int offsetDelta = reader.readUnsignedShort();
					List<VerificationTypeInfo> locals = AttributeHelper.readList(reader, VerificationTypeInfo.CODEC);
					List<VerificationTypeInfo> stack = AttributeHelper.readList(reader, VerificationTypeInfo.CODEC);
					return new FullFrameImpl(offsetDelta, locals, stack);
				}, ExactSkip.U3.then(AttributeHelper.skipN(VerificationTypeInfo.CODEC)).then(AttributeHelper.skipN(VerificationTypeInfo.CODEC))),
				(writer, value) -> {
					writer.writeByte(255);
					writer.writeShort(value.offsetDelta());
					AttributeHelper.writeList(writer, value.locals(), VerificationTypeInfo.CODEC);
					AttributeHelper.writeList(writer, value.stack(), VerificationTypeInfo.CODEC);
				}
		);
	}
}
