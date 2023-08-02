package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;
import dev.xdark.classfile.type.Type;

public interface ConstantTypeInstruction extends ConstantInstruction<LoadableConstant.OfType> {

	static ConstantTypeInstruction create(LoadableConstant.OfType value) {
		return new ConstantInstructionImpl.TypeImpl(value);
	}

	static ConstantTypeInstruction create(Type value) {
		return create(LoadableConstant.create(value));
	}
}
