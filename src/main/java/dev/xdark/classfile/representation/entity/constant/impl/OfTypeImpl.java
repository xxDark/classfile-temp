package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;
import dev.xdark.classfile.representation.instruction.ConstantTypeInstruction;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.Type;

public final class OfTypeImpl implements LoadableConstant.OfType, ConstantInternal {
	private final Type value;

	public OfTypeImpl(Type value) {
		this.value = value;
	}

	@Override
	public void accept(ConstantSink sink) {
		Type type = value;
		if (type instanceof ObjectType) {
			sink.acceptType((ObjectType) type);
		} else {
			sink.acceptType((MethodType) type);
		}
	}

	@Override
	public Type value() {
		return value;
	}

	@Override
	public int store(MutableSymbolTable symtab) {
		return symtab.addType(value);
	}

	@Override
	public ConstantInstruction<?> asInstruction() {
		return ConstantTypeInstruction.create(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OfTypeImpl that = (OfTypeImpl) o;

		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
