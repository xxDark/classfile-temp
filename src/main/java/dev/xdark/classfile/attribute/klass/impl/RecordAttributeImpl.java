package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.RecordAttribute;
import dev.xdark.classfile.attribute.klass.RecordComponent;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.List;

public final class RecordAttributeImpl implements RecordAttribute {
	private final List<RecordComponent> components;

	public RecordAttributeImpl(List<RecordComponent> components) {
		this.components = components;
	}

	@Override
	public List<RecordComponent> components() {
		return components;
	}

	@Override
	public AttributeInfo<RecordAttribute> info() {
		return AttributeInfo.Record;
	}

	public static Codec<RecordAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new RecordAttributeImpl(AttributeHelper.readList(reader, RecordComponent.CODEC));
				}, VariableSkip.U4),
				(writer, value) -> AttributeHelper.writeList(writer, value.components(), RecordComponent.CODEC)
		);
	}
}
