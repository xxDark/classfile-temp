package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.constantpool.ConstantFloat;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;
import dev.xdark.classfile.representation.instruction.ConstantLongInstruction;

public final class OfLongImpl implements LoadableConstant.OfLong, ConstantInternal {
	private final long value;

	public OfLongImpl(long value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptLong(value);
	}

	@Override
	public long value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.constantPool().add(ConstantFloat.create(value));
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantLongInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfLongImpl that = (OfLongImpl) o;

		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(value);
	}
}
