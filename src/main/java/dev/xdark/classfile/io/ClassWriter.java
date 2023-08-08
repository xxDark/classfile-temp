package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.Tag;

import java.io.IOException;

public interface ClassWriter extends BinaryOutput {

	ClassFileVersion version();

	MutableConstantPool constantPool();

	AttributeMapper attributeMapper();

	default void writeConstantPoolTag(Tag<?> tag) {
		writeByte(tag.id());
	}

	default void writeConstantPoolIndex(int index) {
		writeShort(index);
	}

	default void writeAccessFlags(int accessFlags) {
		writeShort(accessFlags);
	}

	default void writeAttributeLength(long length) {
		writeUnsignedInt(length);
	}
}
