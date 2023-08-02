package dev.xdark.classfile.attribute.code;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.IndexedAttribute;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.code.impl.CodeAttributeImpl;
import dev.xdark.classfile.io.BinaryInput;

import java.util.List;

public interface CodeAttribute extends SpecAttribute {

	int maxStack();

	int maxLocals();

	BinaryInput bytecode();

	List<ExceptionTableEntry> exceptionTable();

	List<IndexedAttribute> attributes();

	CodeAttribute withBytecode(BinaryInput bytecode);

	@Override
	AttributeInfo<CodeAttribute> info();

	static CodeAttribute create(int maxStack, int maxLocals, BinaryInput bytecode, List<ExceptionTableEntry> exceptionTable, List<IndexedAttribute> attributes) {
		return new CodeAttributeImpl(maxStack, maxLocals, bytecode, exceptionTable, attributes);
	}
}
