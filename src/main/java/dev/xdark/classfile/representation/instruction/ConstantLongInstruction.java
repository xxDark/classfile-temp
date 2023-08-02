package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantLongInstruction extends ConstantInstruction<LoadableConstant.OfLong> {

	static ConstantLongInstruction create(LoadableConstant.OfLong value) {
		return new ConstantInstructionImpl.LongImpl(value);
	}

	static ConstantLongInstruction create(long value) {
		return create(LoadableConstant.create(value));
	}
}
