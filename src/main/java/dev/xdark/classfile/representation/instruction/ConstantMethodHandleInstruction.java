package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.instruction.impl.ConstantInstructionImpl;

public interface ConstantMethodHandleInstruction extends ConstantInstruction<LoadableConstant.OfMethodHandle> {

	static ConstantMethodHandleInstruction create(LoadableConstant.OfMethodHandle value) {
		return new ConstantInstructionImpl.MethodHandleImpl(value);
	}

	static ConstantMethodHandleInstruction create(MethodHandle value) {
		return create(LoadableConstant.create(value));
	}
}
