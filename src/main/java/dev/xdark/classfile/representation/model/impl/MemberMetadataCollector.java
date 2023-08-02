package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.attribute.Attributable;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.attribute.shared.SignatureAttribute;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.type.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MemberMetadataCollector implements Attributable, AttributesVisitor {
	final List<UnrecognizedAttribute> unrecognizedAttributes = new ArrayList<>();
	protected final SymbolTable symbolTable;
	protected final int accessFlags;
	protected final String name;
	protected final Type type;
	String signature;

	MemberMetadataCollector(SymbolTable symbolTable, int accessFlags, int name, int type) {
		this.symbolTable = symbolTable;
		this.accessFlags = accessFlags;
		ConstantPool constantPool = symbolTable.constantPool();
		this.name = constantPool.get(name, Tag.Utf8).value();
		this.type = Type.ofDescriptor(constantPool.get(type, Tag.Utf8).value());
	}

	@Override
	public final AttributesVisitor visitAttributes() {
		return this;
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		if (attribute instanceof SignatureAttribute) {
			signature = symbolTable.constantPool().get(((SignatureAttribute) attribute).signatureIndex(), Tag.Utf8).value();
		}
	}

	@Override
	public final void visit(int nameIndex, UnknownAttribute attribute) throws IOException {
		unrecognizedAttributes.add(UnrecognizedAttribute.create(
				symbolTable.constantPool().get(nameIndex, Tag.Utf8).value(),
				attribute.payload().detach()
		));
	}
}
