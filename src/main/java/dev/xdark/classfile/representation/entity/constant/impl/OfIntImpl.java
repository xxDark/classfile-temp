package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.constantpool.ConstantFloat;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;
import dev.xdark.classfile.representation.instruction.ConstantIntInstruction;

public final class OfIntImpl implements LoadableConstant.OfInt, ConstantInternal {
	private final int value;

	public OfIntImpl(int value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptInt(value);
	}

	@Override
	public int value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.constantPool().add(ConstantFloat.create(value));
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantIntInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfIntImpl that = (OfIntImpl) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
