package dev.xdark.classfile.representation;

import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.impl.ProxyingSymbolTable;
import dev.xdark.classfile.representation.impl.SymbolTableImpl;

public interface SymbolTable {

	ConstantPool constantPool();

	BootstrapMethodsAttribute bootstrapMethodsAttribute();

	LoadableConstant getConstant(int index);

	InvokeDynamic getInvokeDynamic(int index);

	ConstantDynamic getConstantDynamic(int index);

	static SymbolTable wrap(ConstantPool constantPool, BootstrapMethodsAttribute attribute) {
		return new SymbolTableImpl(constantPool, attribute);
	}

	static SymbolTable wrap(ConstantPool constantPool) {
		return new ProxyingSymbolTable(constantPool);
	}
}
