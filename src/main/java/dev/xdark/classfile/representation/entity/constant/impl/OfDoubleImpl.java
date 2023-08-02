package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.constantpool.ConstantDouble;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.instruction.ConstantDoubleInstruction;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;

public final class OfDoubleImpl implements LoadableConstant.OfDouble, ConstantInternal {
	private final double value;

	public OfDoubleImpl(double value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptDouble(value);
	}

	@Override
	public double value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.constantPool().add(ConstantDouble.create(value));
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantDoubleInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfDoubleImpl that = (OfDoubleImpl) o;

		return Double.compare(that.value, value) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(value);
	}
}
