package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.Type;

public interface MemberInstruction extends Instruction {

	ObjectType owner();

	String name();

	Type type();
}
