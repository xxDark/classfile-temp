package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.type.ClassType;

public interface ArrayStoreInstruction extends Instruction {

	static ArrayStoreInstruction create(ClassType componentType) {
		return visitor -> visitor.arrayStore(componentType);
	}
}
