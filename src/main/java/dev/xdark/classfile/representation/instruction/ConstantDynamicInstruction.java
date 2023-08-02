package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantDynamicInstruction extends ConstantInstruction<LoadableConstant.OfDynamic> {

	static ConstantDynamicInstruction create(LoadableConstant.OfDynamic value) {
		return new ConstantInstructionImpl.DynamicImpl(value);
	}

	static ConstantDynamicInstruction create(ConstantDynamic value) {
		return create(LoadableConstant.create(value));
	}
}
