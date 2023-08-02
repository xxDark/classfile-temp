package dev.xdark.classfile.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.io.BinaryInput;

import java.io.IOException;

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
	public void position(long position) throws IOException {
		input.position(position);
	}

	@Override
	public ClassReader fork(long position) throws IOException {
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
	public void limit(long limit) throws IOException {
		input.limit(limit);
	}

	@Override
	public int readByte() throws IOException {
		return input.readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return input.readUnsignedByte();
	}

	@Override
	public int readShort() throws IOException {
		return input.readShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return input.readUnsignedShort();
	}

	@Override
	public char readChar() throws IOException {
		return input.readChar();
	}

	@Override
	public int readInt() throws IOException {
		return input.readInt();
	}

	@Override
	public long readUnsignedInt() throws IOException {
		return input.readUnsignedInt();
	}

	@Override
	public long readLong() throws IOException {
		return input.readLong();
	}

	@Override
	public float readFloat() throws IOException {
		return input.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return input.readDouble();
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return input.read(b, off, len);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		input.readFully(b, off, len);
	}

	@Override
	public String readUtf() throws IOException {
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
	public long skip(long bytes) throws IOException {
		return input.skip(bytes);
	}

	@Override
	public ClassReader detach() throws IOException {
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
