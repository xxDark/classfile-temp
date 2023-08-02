package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.constantpool.ConstantString;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;
import dev.xdark.classfile.representation.instruction.ConstantStringInstruction;

public final class OfStringImpl implements LoadableConstant.OfString, ConstantInternal {
	private final String value;

	public OfStringImpl(String value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptString(value);
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		MutableConstantPool constantPool = symtab.constantPool();
		return constantPool.add(ConstantString.create(constantPool.add(ConstantUtf8.create(value))));
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantStringInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfStringImpl ofString = (OfStringImpl) o;

		return value.equals(ofString.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
