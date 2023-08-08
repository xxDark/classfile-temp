package dev.xdark.classfile.attribute;

import dev.xdark.classfile.io.ClassWriter;
import dev.xdark.classfile.io.Codec;

import java.io.IOException;
import java.util.Collection;

public final class AttributeWriter {

	private AttributeWriter() {
	}

	public static void write(ClassWriter writer, IndexedAttribute attribute) {
		writer.writeConstantPoolIndex(attribute.getNameIndex());
		Attribute attr = attribute.getAttribute();
		if (attr instanceof SpecAttribute) {
			SpecAttribute spec = (SpecAttribute) attr;
			// noinspection rawtypes,unchecked
			((Codec) spec.info().codec()).write(writer, spec);
		} else {
			UnknownAttribute.CODEC.write(writer, (UnknownAttribute) attr);
		}
	}

	public static void writeAll(ClassWriter writer, Collection<IndexedAttribute> attributes) {
		writer.writeShort(attributes.size());
		for (IndexedAttribute attribute : attributes) {
			write(writer, attribute);
		}
	}
}
