package dev.xdark.classfile.attribute.shared.annotation.impl;

import dev.xdark.classfile.attribute.shared.annotation.ElementConstant;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

import java.util.function.IntFunction;

abstract class ElementConstantImpl implements ElementConstant {

	private final int constantIndex;

	ElementConstantImpl(int constantIndex) {
		this.constantIndex = constantIndex;
	}

	@Override
	public final int constantIndex() {
		return constantIndex;
	}

	protected static <E extends ElementConstant> Codec<E> makeCodec(IntFunction<? extends E> fn) {
		return Codec.wire(
				Input.wire(reader -> fn.apply(reader.readConstantPoolIndex()), ExactSkip.U2),
				(writer, value) -> writer.writeConstantPoolIndex(value.constantIndex())
		);
	}
}
