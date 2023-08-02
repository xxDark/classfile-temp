package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.BadClassFileFormatException;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementArray;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

import java.util.ArrayList;
import java.util.List;

public final class ElementArrayImpl implements ElementArray {
	private final List<Element> elements;

	public ElementArrayImpl(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public List<Element> elements() {
		return elements;
	}

	@Override
	public ElementDescriptor<ElementArray> descriptor() {
		return ElementDescriptor.ARRAY;
	}

	public static Codec<ElementArray> codec() {
		return Codec.wire(Input.wire(reader -> {
			int count = reader.readUnsignedShort();
			List<Element> elements = new ArrayList<>();
			while (count-- != 0) {
				int rawTag = reader.readUnsignedByte();
				ElementDescriptor<?> descriptor = ElementDescriptor.byTag(rawTag);
				if (descriptor == null) {
					throw new BadClassFileFormatException(String.format("Unknown annotation tag %s", (char) rawTag));
				}
				elements.add(descriptor.codec().read(reader));
			}
			return new ElementArrayImpl(elements);
		}, reader -> {
			int count = reader.readUnsignedShort();
			while (count-- != 0) {
				int rawTag = reader.readUnsignedByte();
				ElementDescriptor<?> descriptor = ElementDescriptor.byTag(rawTag);
				if (descriptor == null) {
					throw new BadClassFileFormatException(String.format("Unknown annotation tag %s", (char) rawTag));
				}
				descriptor.codec().skip(reader);
			}
		}), (writer, value) -> {
			List<Element> elements = value.elements();
			writer.writeShort(elements.size());
			for (Element element : elements) {
				ElementDescriptor<?> descriptor = element.descriptor();
				writer.writeByte(descriptor.tag());
				//noinspection unchecked,rawtypes
				((Codec) descriptor.codec()).write(writer, element);
			}
		});
	}
}
