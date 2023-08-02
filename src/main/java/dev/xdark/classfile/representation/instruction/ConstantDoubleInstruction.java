package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantDoubleInstruction extends ConstantInstruction<LoadableConstant.OfDouble> {

	static ConstantDoubleInstruction create(LoadableConstant.OfDouble value) {
		return new ConstantInstructionImpl.DoubleImpl(value);
	}

	static ConstantDoubleInstruction create(double value) {
		return create(LoadableConstant.create(value));
	}
}
