package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.io.UncheckedIOException;
import dev.xdark.classfile.attribute.shared.annotation.AnnotationVisitor;
import dev.xdark.classfile.attribute.shared.annotation.ArrayVisitor;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.ElementArray;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueArray;

import java.util.ArrayList;
import java.util.List;

public final class ElementArrayImpl implements ElementArray, ElementInternal {
	private final List<Element> elements;

	public ElementArrayImpl(List<Element> elements) {
		this.elements = elements;
	}

	@Override
	public List<Element> elements() {
		return elements;
	}

	@Override
	public void accept(ArrayVisitor visitor) {
		for (Element element : elements) {
			if (element instanceof ElementAnnotation) {
				ElementAnnotation annotation = (ElementAnnotation) element;
				AnnotationVisitor nested = visitor.visitAnnotation(annotation.typeIndex());
				if (nested != null) {
					annotation.accept(nested);
				}
			} else if (element instanceof ElementArray) {
				ElementArray array = (ElementArray) element;
				ArrayVisitor nested = visitor.visitArray();
				if (nested != null) {
					array.accept(nested);
				}
			} else {
				visitor.visit(element);
			}
		}
		visitor.visitEnd();
	}

	@Override
	public ElementDescriptor<ElementArray> descriptor() {
		return ElementDescriptor.ARRAY;
	}

	@Override
	public AnnotationValue normalise(ConstantPool constantPool) {
		List<AnnotationValue> values = new ArrayList<>();
		for (Element element : elements) {
			values.add(((ElementInternal) element).normalise(constantPool));
		}
		return ValueArray.create(values);
	}

	public static Codec<ElementArray> codec() {
		return Codec.wire(Input.wire(reader -> {
			int count = reader.readUnsignedShort();
			List<Element> elements = new ArrayList<>();
			while (count-- != 0) {
				int rawTag = reader.readUnsignedByte();
				ElementDescriptor<?> descriptor = ElementDescriptor.byTag(rawTag);
				if (descriptor == null) {
					throw new UncheckedIOException(String.format("Unknown annotation tag %s", (char) rawTag));
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
					throw new UncheckedIOException(String.format("Unknown annotation tag %s", (char) rawTag));
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
