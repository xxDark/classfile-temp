package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;
import dev.xdark.classfile.representation.instruction.ConstantMethodHandleInstruction;

public final class OfMethodHandleImpl implements LoadableConstant.OfMethodHandle, ConstantInternal {
	private final MethodHandle value;

	public OfMethodHandleImpl(MethodHandle value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		sink.acceptMethodHandle(value);
	}

	@Override
	public MethodHandle value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.addMethodHandle(value);
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantMethodHandleInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfMethodHandleImpl that = (OfMethodHandleImpl) o;

		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
