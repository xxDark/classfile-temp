package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.constantpool.ConstantFloat;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.instruction.ConstantFloatInstruction;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;

public final class OfFloatImpl implements LoadableConstant.OfFloat, ConstantInternal {
	private final float value;

	public OfFloatImpl(float value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptFloat(value);
	}

	@Override
	public float value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.constantPool().add(ConstantFloat.create(value));
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantFloatInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfFloatImpl that = (OfFloatImpl) o;

		return Float.compare(that.value, value) == 0;
	}

	@Override
	public int hashCode() {
		return Float.hashCode(value);
	}
}
