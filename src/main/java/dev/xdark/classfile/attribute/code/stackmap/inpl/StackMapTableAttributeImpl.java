package dev.xdark.classfile.attribute.code.stackmap.inpl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.code.stackmap.StackMapTableAttribute;
import dev.xdark.classfile.attribute.code.stackmap.frame.StackMapFrame;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.List;

public final class StackMapTableAttributeImpl implements StackMapTableAttribute {
	private final List<StackMapFrame> frames;

	public StackMapTableAttributeImpl(List<StackMapFrame> frames) {
		this.frames = frames;
	}

	@Override
	public List<StackMapFrame> frames() {
		return frames;
	}

	@Override
	public AttributeInfo<StackMapTableAttribute> info() {
		return AttributeInfo.StackMapTable;
	}

	public static Codec<StackMapTableAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new StackMapTableAttributeImpl(AttributeHelper.readList(reader, StackMapFrame.CODEC));
				}, VariableSkip.U4),
				(writer, value) -> AttributeHelper.writeList(writer, value.frames(), StackMapFrame.CODEC)
		);
	}
}
