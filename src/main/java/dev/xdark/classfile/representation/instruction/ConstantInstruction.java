package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.entity.constant.impl.ConstantInternal;

public interface ConstantInstruction<C extends LoadableConstant> extends Instruction {

	C constant();

	static <C extends LoadableConstant> ConstantInstruction<C> create(C constant) {
		//noinspection unchecked
		return (ConstantInstruction<C>) ((ConstantInternal) constant).asInstruction();
	}
}
