package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.instruction.ConstantDynamicInstruction;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;

public final class OfDynamicImpl implements LoadableConstant.OfDynamic, ConstantInternal {
	private final ConstantDynamic value;

	public OfDynamicImpl(ConstantDynamic value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptDynamic(value);
	}

	@Override
	public ConstantDynamic value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.addConstantDynamic(value);
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantDynamicInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfDynamicImpl ofString = (OfDynamicImpl) o;

		return value.equals(ofString.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
