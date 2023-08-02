package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.bytecode.PrimitiveConversion;
import dev.xdark.classfile.bytecode.PrimitiveConversions;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.PrimitiveConversionInstruction;
import dev.xdark.classfile.type.PrimitiveType;

public final class PrimitiveConversionInstructionImpl implements PrimitiveConversionInstruction {
	private final PrimitiveType from, to;

	public PrimitiveConversionInstructionImpl(PrimitiveType from, PrimitiveType to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public PrimitiveType from() {
		return from;
	}

	@Override
	public PrimitiveType to() {
		return to;
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		visitor.primitiveConversion(from, to);
	}

	@Override
	public void accept(PrimitiveConversion conversion) {
		PrimitiveConversions.performConversion(conversion, from, to);
	}
}
