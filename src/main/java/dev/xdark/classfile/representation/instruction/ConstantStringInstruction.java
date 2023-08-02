package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantStringInstruction extends ConstantInstruction<LoadableConstant.OfString> {

	static ConstantStringInstruction create(LoadableConstant.OfString value) {
		return new ConstantInstructionImpl.StringImpl(value);
	}

	static ConstantStringInstruction create(String value) {
		return create(LoadableConstant.create(value));
	}
}
