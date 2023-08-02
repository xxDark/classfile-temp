package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeCollector;
import dev.xdark.classfile.attribute.AttributeLocation;
import dev.xdark.classfile.attribute.AttributeWriter;
import dev.xdark.classfile.attribute.IndexedAttribute;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.internal.AttributeReader;
import dev.xdark.classfile.attribute.klass.RecordComponent;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

import java.util.ArrayList;
import java.util.List;

public final class RecordComponentImpl implements RecordComponent {
	private final int nameIndex;
	private final int descriptorIndex;
	private final List<IndexedAttribute> attributes;

	RecordComponentImpl(int nameIndex, int descriptorIndex, List<IndexedAttribute> attributes) {
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributes = attributes;
	}

	@Override
	public int nameIndex() {
		return nameIndex;
	}

	@Override
	public int descriptorIndex() {
		return descriptorIndex;
	}

	@Override
	public List<IndexedAttribute> attributes() {
		return attributes;
	}

	public static Codec<RecordComponent> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					AttributeHelper.checkAtLeast(reader.readAttributeLength(), 6L);
					int nameIndex = reader.readConstantPoolIndex();
					int descriptorIndex = reader.readConstantPoolIndex();
					List<IndexedAttribute> attributes = new ArrayList<>();
					AttributeReader.readAll(reader, AttributeLocation.RECORD_COMPONENT, new AttributeCollector(attributes::add));
					return new RecordComponentImpl(nameIndex, descriptorIndex, attributes);
				}, ExactSkip.U4.then(AttributeHelper.skipUnsignedShorts())),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.nameIndex());
					writer.writeConstantPoolIndex(value.descriptorIndex());
					AttributeWriter.writeAll(writer, value.attributes());
				}
		);
	}
}
