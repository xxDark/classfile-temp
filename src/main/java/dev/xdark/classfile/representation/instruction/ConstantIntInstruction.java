package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantIntInstruction extends ConstantInstruction<LoadableConstant.OfInt> {

	static ConstantIntInstruction create(LoadableConstant.OfInt value) {
		return new ConstantInstructionImpl.IntImpl(value);
	}

	static ConstantIntInstruction create(int value) {
		return create(LoadableConstant.create(value));
	}
}
