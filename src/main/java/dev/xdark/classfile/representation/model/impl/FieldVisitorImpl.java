package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.field.ConstantValueAttribute;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.model.impl.MemberMetadataCollector;

class FieldVisitorImpl extends MemberMetadataCollector implements FieldVisitor {
	LoadableConstant constantValue;

	FieldVisitorImpl(SymbolTable symbolTable, int accessFlags, int name, int type) {
		super(symbolTable, accessFlags, name, type);
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		super.visit(nameIndex, attribute);
		if (attribute instanceof ConstantValueAttribute) {
			constantValue = symbolTable.getConstant(((ConstantValueAttribute) attribute).constantIndex());
		}
	}
}
