package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;

public final class ProxyingSymbolTable implements SymbolTable {
	private final ConstantPool constantPool;

	public ProxyingSymbolTable(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	@Override
	public ConstantPool constantPool() {
		return constantPool;
	}

	@Override
	public BootstrapMethodsAttribute bootstrapMethodsAttribute() {
		throw new IllegalStateException("BootstrapMethodsAttribute not available");
	}

	@Override
	public LoadableConstant getConstant(int index) {
		return SymbolTableHelper.asLoadable(this, index);
	}

	@Override
	public InvokeDynamic getInvokeDynamic(int index) {
		throw new IllegalStateException("Cannot decode InvokeDynamic without BootstrapMethodAttribute");
	}

	@Override
	public ConstantDynamic getConstantDynamic(int index) {
		throw new IllegalStateException("Cannot decode ConstantDynamic without BootstrapMethodAttribute");
	}
}
