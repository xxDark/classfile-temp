package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.type.ClassType;

public interface ArrayLoadInstruction extends Instruction {

	static ArrayLoadInstruction create(ClassType componentType) {
		return visitor -> visitor.arrayLoad(componentType);
	}
}
