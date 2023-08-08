package dev.xdark.classfile.attribute.internal;

import dev.xdark.classfile.io.UncheckedIOException;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.AttributeLocation;
import dev.xdark.classfile.attribute.AttributeMapperResult;
import dev.xdark.classfile.attribute.AttributeMapperResults;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.constantpool.Tag;

public final class AttributeReader {

	private AttributeReader() {
	}

	public static void read(ClassReader cr, AttributeLocation location, AttributesVisitor visitor) {
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
		done:
		{
			try_read:
			if (info != null) {
				SpecAttribute read;
				try {
					read = info.codec().read(cr);
				} catch (UncheckedIOException ignored) {
					cr.position(position);
					break try_read;
				}
				visitor.visit(nameIndex, read);
				break done;
			}
			visitor.visit(nameIndex, UnknownAttribute.CODEC.read(cr));
		}
		if (cr.position() - position - 4L != length) {
			throw new RuntimeException(String.format("Not the whole attribute was consumed: %s", name));
		}
	}

	public static void readAll(ClassReader cr, AttributeLocation location, AttributesVisitor visitor) {
		//    u2             attributes_count;
		//    attribute_info attributes[attributes_count];
		int attrCount = cr.readUnsignedShort();
		while (attrCount-- != 0) {
			read(cr, location, visitor);
		}
	}

	public static void skip(ClassReader cr) {
		// attribute_info {
		//    u2 attribute_name_index;
		//    u4 attribute_length;
		//    u1 info[attribute_length];
		// }
		cr.skipBytes(2L);
		cr.skipBytes(cr.readAttributeLength());
	}

	public static void skipAll(ClassReader cr) {
		//    u2             attributes_count;
		//    attribute_info attributes[attributes_count];
		int attrCount = cr.readUnsignedShort();
		while (attrCount-- != 0) {
			skip(cr);
		}
	}
}
