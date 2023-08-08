package dev.xdark.classfile.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.io.BinaryOutput;
import dev.xdark.classfile.io.ClassWriter;

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
	public void position(long position) {
		output.position(position);
	}

	@Override
	public void writeByte(int value) {
		output.writeByte(value);
	}

	@Override
	public void writeShort(int value) {
		output.writeShort(value);
	}

	@Override
	public void writeChar(char value) {
		output.writeChar(value);
	}

	@Override
	public void writeInt(int value) {
		output.writeInt(value);
	}

	@Override
	public void writeUnsignedInt(long value) {
		output.writeUnsignedInt(value);
	}

	@Override
	public void writeLong(long value) {
		output.writeLong(value);
	}

	@Override
	public void writeFloat(float value) {
		output.writeFloat(value);
	}

	@Override
	public void writeDouble(double value) {
		output.writeDouble(value);
	}

	@Override
	public void writeUtf(String utf) {
		output.writeUtf(utf);
	}

	@Override
	public void write(byte[] b, int off, int len) {
		output.write(b, off, len);
	}

	@Override
	public void write(byte[] b) {
		output.write(b);
	}
}
