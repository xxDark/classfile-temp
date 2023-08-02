package dev.xdark.classfile.attribute.code.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.code.LineNumber;
import dev.xdark.classfile.attribute.code.LineNumberTableAttribute;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.List;

public final class LineNumberTableAttributeImpl implements LineNumberTableAttribute {
	private final List<LineNumber> lineNumbers;

	public LineNumberTableAttributeImpl(List<LineNumber> lineNumbers) {
		this.lineNumbers = lineNumbers;
	}

	@Override
	public List<LineNumber> lineNumbers() {
		return lineNumbers;
	}

	@Override
	public AttributeInfo<LineNumberTableAttribute> info() {
		return AttributeInfo.LineNumberTable;
	}

	public static Codec<LineNumberTableAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new LineNumberTableAttributeImpl(AttributeHelper.readList(reader, LineNumber.CODEC));
				}, VariableSkip.U4),
				(writer, value) -> AttributeHelper.writeList(writer, value.lineNumbers(), LineNumber.CODEC)
		);
	}
}
