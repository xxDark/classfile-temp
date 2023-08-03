package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.field.ConstantValueAttribute;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.constant.impl.ConstantInternal;

public final class FieldVisitorWriter extends  MemberVisitorWriter implements FieldVisitor {

	public FieldVisitorWriter(MutableSymbolTable symbolTable, dev.xdark.classfile.FieldVisitor visitor) {
		super(symbolTable, visitor);
	}

	@Override
	public void visitConstantValue(LoadableConstant constant) {
		dev.xdark.classfile.attribute.AttributesVisitor av = visitor.visitAttributes();
		if (av != null) {
			MutableSymbolTable symbolTable = symtab;
			MutableConstantPool constantPool = symbolTable.constantPool();
			av.visit(constantPool.add(ConstantUtf8.create(AttributeInfo.ConstantValue.name())), ConstantValueAttribute.create(((ConstantInternal) constant).store(symbolTable)));
		}
	}
}
