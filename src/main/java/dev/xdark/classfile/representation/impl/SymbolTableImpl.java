package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.ConstantInvokeDynamic;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.constantpool.impl.ConstantInternal;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.impl.Decoder;

public class SymbolTableImpl implements SymbolTable {
	protected final ConstantPool constantPool;
	protected final BootstrapMethodsAttribute bootstrapMethodsAttribute;
	private Decoder decoder;

	public SymbolTableImpl(ConstantPool constantPool, BootstrapMethodsAttribute bootstrapMethodsAttribute) {
		this.constantPool = constantPool;
		this.bootstrapMethodsAttribute = bootstrapMethodsAttribute;
	}

	@Override
	public ConstantPool constantPool() {
		return constantPool;
	}

	@Override
	public BootstrapMethodsAttribute bootstrapMethodsAttribute() {
		return bootstrapMethodsAttribute;
	}

	@Override
	public LoadableConstant getConstant(int index) {
		return ((ConstantInternal) constantPool.get(index)).asLoadable(this);
	}

	@Override
	public final InvokeDynamic getInvokeDynamic(int index) {
		ConstantInvokeDynamic cst = constantPool.get(index, Tag.InvokeDynamic);
		return decoder().decodeInvokeDynamic(index, cst, bootstrapMethodsAttribute);
	}

	@Override
	public final ConstantDynamic getConstantDynamic(int index) {
		dev.xdark.classfile.constantpool.ConstantDynamic cst = constantPool.get(index, Tag.ConstantDynamic);
		return decoder().decodeConstantDynamic(index, cst, bootstrapMethodsAttribute);
	}

	private Decoder decoder() {
		Decoder decoder = this.decoder;
		if (decoder == null) {
			decoder = new Decoder(this);
			this.decoder = decoder;
		}
		return decoder;
	}
}
