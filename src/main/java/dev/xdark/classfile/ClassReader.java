package dev.xdark.classfile;

import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.io.BinaryInput;

import java.io.IOException;

public interface ClassReader extends BinaryInput {

	ClassFileVersion version();

	ConstantPool constantPool();

	AttributeMapper attributeMapper();

	@Override
	ClassReader fork(long position) throws IOException;

	@Override
	ClassReader duplicate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	ClassReader detach() throws IOException;

	default long readUnsignedLong() throws IOException {
		return Integer.toUnsignedLong(readInt());
	}

	default int readConstantPoolIndex() throws IOException {
		return readUnsignedShort();
	}

	default int readAccessFlags() throws IOException {
		return readUnsignedShort();
	}

	default long readAttributeLength() throws IOException {
		return readUnsignedInt();
	}

	default void skipConstantPoolIndex() throws IOException {
		skipBytes(2L);
	}

	default void skipAttributeLength() throws IOException {
		skipBytes(4L);
	}
}
