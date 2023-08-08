package dev.xdark.classfile;

import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.io.BinaryInput;

public interface ClassReader extends BinaryInput {

	ClassFileVersion version();

	ConstantPool constantPool();

	AttributeMapper attributeMapper();

	@Override
	ClassReader fork(long position);

	@Override
	ClassReader duplicate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	ClassReader detach();

	default long readUnsignedLong() {
		return Integer.toUnsignedLong(readInt());
	}

	default int readConstantPoolIndex() {
		return readUnsignedShort();
	}

	default int readAccessFlags() {
		return readUnsignedShort();
	}

	default long readAttributeLength() {
		return readUnsignedInt();
	}

	default void skipConstantPoolIndex() {
		skipBytes(2L);
	}

	default void skipAttributeLength() {
		skipBytes(4L);
	}
}
