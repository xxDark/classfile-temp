package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.representation.model.CodeModel;
import dev.xdark.classfile.representation.SymbolTable;

class MethodVisitorImpl extends MemberMetadataCollector {
	CodeModel code;

	MethodVisitorImpl(SymbolTable symbolTable, int accessFlags, int name, int type) {
		super(symbolTable, accessFlags, name, type);
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		super.visit(nameIndex, attribute);
	}
}
