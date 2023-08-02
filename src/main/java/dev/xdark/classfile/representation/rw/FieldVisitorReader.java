package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.field.ConstantValueAttribute;
import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

final class FieldVisitorReader extends MemberVisitorReader implements dev.xdark.classfile.FieldVisitor {

	FieldVisitorReader(FieldVisitor fv, SymbolTable symbolTable) {
		super(fv, symbolTable);
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		if (attribute instanceof ConstantValueAttribute) {
			LoadableConstant cst = symbolTable.getConstant(((ConstantValueAttribute) attribute).constantIndex());
			((FieldVisitor) mv).visitConstantValue(cst);
			return;
		}
		super.visit(nameIndex, attribute);
	}
}
