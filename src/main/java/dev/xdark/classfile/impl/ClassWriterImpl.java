package dev.xdark.classfile.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.io.BinaryOutput;
import dev.xdark.classfile.io.ClassWriter;

import java.io.IOException;

final class ClassWriterImpl implements ClassWriter {
	private final BinaryOutput output;
	private final ClassFileVersion version;
	private final MutableConstantPool constantPool;
	private final AttributeMapper attributeMapper;

	ClassWriterImpl(BinaryOutput output, ClassFileVersion version, MutableConstantPool constantPool, AttributeMapper attributeMapper) {
		this.output = output;
		this.version = version;
		this.constantPool = constantPool;
		this.attributeMapper = attributeMapper;
	}

	@Override
	public ClassFileVersion version() {
		return version;
	}

	@Override
	public MutableConstantPool constantPool() {
		return constantPool;
	}

	@Override
	public AttributeMapper attributeMapper() {
		return attributeMapper;
	}

	@Override
	public long position() {
		return output.position();
	}

	@Override
	public void position(long position) throws IOException {
		output.position(position);
	}

	@Override
	public void writeByte(int value) throws IOException {
		output.writeByte(value);
	}

	@Override
	public void writeShort(int value) throws IOException {
		output.writeShort(value);
	}

	@Override
	public void writeChar(char value) throws IOException {
		output.writeChar(value);
	}

	@Override
	public void writeInt(int value) throws IOException {
		output.writeInt(value);
	}

	@Override
	public void writeUnsignedInt(long value) throws IOException {
		output.writeUnsignedInt(value);
	}

	@Override
	public void writeLong(long value) throws IOException {
		output.writeLong(value);
	}

	@Override
	public void writeFloat(float value) throws IOException {
		output.writeFloat(value);
	}

	@Override
	public void writeDouble(double value) throws IOException {
		output.writeDouble(value);
	}

	@Override
	public void writeUtf(String utf) throws IOException {
		output.writeUtf(utf);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		output.write(b, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException {
		output.write(b);
	}
}
