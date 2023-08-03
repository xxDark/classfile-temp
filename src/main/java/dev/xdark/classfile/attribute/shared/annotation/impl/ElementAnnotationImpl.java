package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.BadClassFileFormatException;
import dev.xdark.classfile.attribute.shared.annotation.AnnotationVisitor;
import dev.xdark.classfile.attribute.shared.annotation.ArrayVisitor;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.ElementArray;
import dev.xdark.classfile.attribute.shared.annotation.ElementDescriptor;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ElementAnnotationImpl implements ElementAnnotation {
	private final int typeIndex;
	private final int[] nameIndices;
	private final List<Element> values;

	public ElementAnnotationImpl(int typeIndex, int[] nameIndices, List<Element> values) {
		this.typeIndex = typeIndex;
		this.nameIndices = nameIndices;
		this.values = values;
	}

	@Override
	public int typeIndex() {
		return typeIndex;
	}

	@Override
	public int[] nameIndices() {
		return nameIndices;
	}

	@Override
	public List<Element> values() {
		return values;
	}

	@Override
	public void accept(AnnotationVisitor visitor) {
		int[] nameIndices = this.nameIndices;
		Iterator<Element> iterator = values.iterator();
		for (int nameIndex : nameIndices) {
			Element element = iterator.next();
			if (element instanceof ElementAnnotation) {
				ElementAnnotation annotation = (ElementAnnotation) element;
				AnnotationVisitor nested = visitor.visitAnnotation(nameIndex, annotation.typeIndex());
				if (nested != null) {
					annotation.accept(nested);
				}
			} else if (element instanceof ElementArray) {
				ArrayVisitor nested = visitor.visitArray(nameIndex);
				if (nested != null) {
					((ElementArray) element).accept(nested);
				}
			} else {
				visitor.visit(nameIndex, element);
			}
		}
	}

	@Override
	public ElementDescriptor<ElementAnnotation> descriptor() {
		return ElementDescriptor.ANNOTATION;
	}

	public static Codec<ElementAnnotation> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					int typeIndex = reader.readConstantPoolIndex();
					int pairs = reader.readUnsignedShort();
					int[] names = new int[pairs];
					List<Element> elements = new ArrayList<>();
					for (int i = 0; i < pairs; i++) {
						names[i] = reader.readConstantPoolIndex();
						int rawTag = reader.readUnsignedByte();
						ElementDescriptor<?> descriptor = ElementDescriptor.byTag(rawTag);
						if (descriptor == null) {
							throw new BadClassFileFormatException(String.format("Unknown annotation tag %s", (char) rawTag));
						}
						elements.add(descriptor.codec().read(reader));
					}
					return new ElementAnnotationImpl(typeIndex, names, elements);
				}, reader -> {
					reader.skipConstantPoolIndex();
					int pairs = reader.readUnsignedShort();
					while (pairs-- != 0) {
						reader.skipConstantPoolIndex();
						int rawTag = reader.readUnsignedByte();
						ElementDescriptor<?> descriptor = ElementDescriptor.byTag(rawTag);
						if (descriptor == null) {
							throw new BadClassFileFormatException(String.format("Unknown annotation tag %s", (char) rawTag));
						}
						descriptor.codec().skip(reader);
					}
				}),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.typeIndex());
					int[] names = value.nameIndices();
					List<Element> values = value.values();
					int len = names.length;
					if (len != values.size()) {
						throw new BadClassFileFormatException("Sizes do not match");
					}
					writer.writeShort(len);
					for (int i = 0; i < len; i++) {
						writer.writeConstantPoolIndex(names[i]);
						Element element = values.get(i);
						ElementDescriptor<?> descriptor = element.descriptor();
						writer.writeByte(descriptor.tag());
						//noinspection unchecked,rawtypes
						((Codec) descriptor.codec()).write(writer, element);
					}
				}
		);
	}
}
