package dev.xdark.classfile.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.io.BinaryInput;

public final class ClassReaderImpl implements ClassReader {

	ClassFileVersion version;
	ConstantPool constantPool;
	AttributeMapper attributeMapper;
	private final BinaryInput input;

	public ClassReaderImpl(BinaryInput input) {
		this.input = input;
	}

	@Override
	public ClassFileVersion version() {
		return version;
	}

	@Override
	public ConstantPool constantPool() {
		return constantPool;
	}

	@Override
	public AttributeMapper attributeMapper() {
		return attributeMapper;
	}

	@Override
	public long position() {
		return input.position();
	}

	@Override
	public void position(long position) {
		input.position(position);
	}

	@Override
	public ClassReader fork(long position) {
		ClassReaderImpl fork = new ClassReaderImpl(input.fork(position));
		copyTo(fork);
		return fork;
	}

	@Override
	public ClassReader duplicate() {
		ClassReaderImpl dup = new ClassReaderImpl(input.duplicate());
		copyTo(dup);
		return dup;
	}

	@Override
	public void limit(long limit) {
		input.limit(limit);
	}

	@Override
	public int readByte() {
		return input.readByte();
	}

	@Override
	public int readUnsignedByte() {
		return input.readUnsignedByte();
	}

	@Override
	public int readShort() {
		return input.readShort();
	}

	@Override
	public int readUnsignedShort() {
		return input.readUnsignedShort();
	}

	@Override
	public char readChar() {
		return input.readChar();
	}

	@Override
	public int readInt() {
		return input.readInt();
	}

	@Override
	public long readUnsignedInt() {
		return input.readUnsignedInt();
	}

	@Override
	public long readLong() {
		return input.readLong();
	}

	@Override
	public float readFloat() {
		return input.readFloat();
	}

	@Override
	public double readDouble() {
		return input.readDouble();
	}

	@Override
	public int read(byte[] b, int off, int len) {
		return input.read(b, off, len);
	}

	@Override
	public void readFully(byte[] b, int off, int len) {
		input.readFully(b, off, len);
	}

	@Override
	public String readUtf() {
		return input.readUtf();
	}

	@Override
	public boolean isReadable(long bytes) {
		return input.isReadable(bytes);
	}

	@Override
	public long readableBytes() {
		return input.readableBytes();
	}

	@Override
	public long skip(long bytes) {
		return input.skip(bytes);
	}

	@Override
	public ClassReader detach() {
		ClassReaderImpl detached = new ClassReaderImpl(input.detach());
		copyTo(detached);
		return detached;
	}

	private void copyTo(ClassReaderImpl other) {
		other.version = version;
		other.constantPool = constantPool;
		other.attributeMapper = attributeMapper;
	}
}
