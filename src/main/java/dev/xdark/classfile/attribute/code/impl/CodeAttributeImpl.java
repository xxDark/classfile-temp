package dev.xdark.classfile.attribute.code.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.AttributeCollector;
import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.AttributeLocation;
import dev.xdark.classfile.attribute.AttributeWriter;
import dev.xdark.classfile.attribute.IndexedAttribute;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.attribute.code.ExceptionTableEntry;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.internal.AttributeReader;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.ArrayList;
import java.util.List;

public final class CodeAttributeImpl implements CodeAttribute {
	private final int maxStack, maxLocals;
	private final BinaryInput bytecode;
	private final List<ExceptionTableEntry> exceptionTable;
	private final List<IndexedAttribute> attributes;

	public CodeAttributeImpl(int maxStack, int maxLocals, BinaryInput bytecode, List<ExceptionTableEntry> exceptionTable, List<IndexedAttribute> attributes) {
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.bytecode = bytecode;
		this.exceptionTable = exceptionTable;
		this.attributes = attributes;
	}

	@Override
	public int maxStack() {
		return maxStack;
	}

	@Override
	public int maxLocals() {
		return maxLocals;
	}

	@Override
	public BinaryInput bytecode() {
		return bytecode.duplicate();
	}

	@Override
	public List<ExceptionTableEntry> exceptionTable() {
		return exceptionTable;
	}

	@Override
	public List<IndexedAttribute> attributes() {
		return attributes;
	}

	@Override
	public CodeAttribute withBytecode(BinaryInput bytecode) {
		return CodeAttribute.create(maxStack, maxLocals, bytecode, exceptionTable, attributes);
	}

	@Override
	public AttributeInfo<CodeAttribute> info() {
		return AttributeInfo.Code;
	}

	public static Codec<CodeAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength(); // TODO: do at least some verification?
					int maxStack, maxLocals;
					if (reader.version() == ClassFileVersion.V1_0) {
						maxStack = reader.readUnsignedByte();
						maxLocals = reader.readUnsignedByte();
					} else {
						maxStack = reader.readUnsignedShort();
						maxLocals = reader.readUnsignedShort();
					}
					long codeLength = reader.readUnsignedInt();
					long position = reader.position();
					reader.skipBytes(codeLength);
					ClassReader bytecode = reader.fork(position);
					bytecode.limit(codeLength);
					bytecode = bytecode.detach();
					List<ExceptionTableEntry> exceptionTable = AttributeHelper.readList(reader, ExceptionTableEntry.CODEC);
					List<IndexedAttribute> attributes = new ArrayList<>();
					AttributeReader.readAll(reader, AttributeLocation.CODE_ATTRIBUTE, new AttributeCollector(attributes::add));
					return new CodeAttributeImpl(maxStack, maxLocals, bytecode, exceptionTable, attributes);
				}, VariableSkip.U4),
				(writer, value) -> {
					int maxStack = value.maxStack(), maxLocals = value.maxLocals();
					if (writer.version() == ClassFileVersion.V1_0) {
						writer.writeByte(maxStack);
						writer.writeByte(maxLocals);
					} else {
						writer.writeShort(maxStack);
						writer.writeShort(maxLocals);
					}
					BinaryInput bc = value.bytecode();
					writer.writeUnsignedInt(bc.readableBytes());
					byte[] buf = new byte[1024];
					int r;
					while ((r = bc.read(buf)) != -1) {
						writer.write(buf, 0, r);
					}
					AttributeHelper.writeList(writer, value.exceptionTable(), ExceptionTableEntry.CODEC);
					AttributeWriter.writeAll(writer, value.attributes());
				}
		);
	}
}
