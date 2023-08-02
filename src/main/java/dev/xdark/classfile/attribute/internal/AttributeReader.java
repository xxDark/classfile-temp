package dev.xdark.classfile.attribute.internal;

import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.AttributeLocation;
import dev.xdark.classfile.attribute.AttributeMapperResult;
import dev.xdark.classfile.attribute.AttributeMapperResults;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.constantpool.Tag;

import java.io.IOException;

public final class AttributeReader {

	private AttributeReader() {
	}

	public static void read(ClassReader cr, AttributeLocation location, AttributesVisitor visitor) throws IOException {
		// attribute_info {
		//    u2 attribute_name_index;
		//    u4 attribute_length;
		//    u1 info[attribute_length];
		// }
		int nameIndex = cr.readConstantPoolIndex();
		String name = cr.constantPool().get(nameIndex, Tag.Utf8).value();
		AttributeMapperResult result = cr.attributeMapper().getInfo(location, name);
		if (result == AttributeMapperResults.SKIP) {
			cr.skipBytes(cr.readAttributeLength());
			return;
		}
		AttributeInfo<?> info = (AttributeInfo<?>) result;
		long position = cr.position();
		long length = cr.fork(position).readAttributeLength();
		if (info == null) {
			visitor.visit(nameIndex, UnknownAttribute.CODEC.read(cr));
		} else {
			visitor.visit(nameIndex, info.codec().read(cr));
		}
		if (cr.position() - position - 4L != length) {
			throw new RuntimeException(String.format("Not the whole attribute was consumed: %s", name));
		}
	}

	public static void readAll(ClassReader cr, AttributeLocation location, AttributesVisitor visitor) throws IOException {
		//    u2             attributes_count;
		//    attribute_info attributes[attributes_count];
		int attrCount = cr.readUnsignedShort();
		while (attrCount-- != 0) {
			read(cr, location, visitor);
		}
	}

	public static void skip(ClassReader cr) throws IOException {
		// attribute_info {
		//    u2 attribute_name_index;
		//    u4 attribute_length;
		//    u1 info[attribute_length];
		// }
		cr.skipBytes(2L);
		cr.skipBytes(cr.readAttributeLength());
	}

	public static void skipAll(ClassReader cr) throws IOException {
		//    u2             attributes_count;
		//    attribute_info attributes[attributes_count];
		int attrCount = cr.readUnsignedShort();
		while (attrCount-- != 0) {
			skip(cr);
		}
	}
}
