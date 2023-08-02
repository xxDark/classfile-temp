package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantFloatInstruction extends ConstantInstruction<LoadableConstant.OfFloat> {

	static ConstantFloatInstruction create(LoadableConstant.OfFloat value) {
		return new ConstantInstructionImpl.FloatImpl(value);
	}

	static ConstantFloatInstruction create(float value) {
		return create(LoadableConstant.create(value));
	}
}
