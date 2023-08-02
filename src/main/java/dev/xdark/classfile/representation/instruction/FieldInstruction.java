package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.FieldInstructionImpl;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;

public interface FieldInstruction extends MemberInstruction {

	@Override
	InstanceType owner();

	@Override
	ClassType type();

	Mode mode();

	static FieldInstruction create(Mode mode, InstanceType owner, String name, ClassType type) {
		return new FieldInstructionImpl(owner, name, type, mode);
	}

	static FieldInstruction getStatic(InstanceType owner, String name, ClassType type) {
		return create(Mode.GETSTATIC, owner, name, type);
	}

	static FieldInstruction putStatic(InstanceType owner, String name, ClassType type) {
		return create(Mode.PUTSTATIC, owner, name, type);
	}

	static FieldInstruction getField(InstanceType owner, String name, ClassType type) {
		return create(Mode.GETFIELD, owner, name, type);
	}

	static FieldInstruction putField(InstanceType owner, String name, ClassType type) {
		return create(Mode.PUTFIELD, owner, name, type);
	}

	enum Mode {
		GETSTATIC,
		PUTSTATIC,
		GETFIELD,
		PUTFIELD,
	}
}
