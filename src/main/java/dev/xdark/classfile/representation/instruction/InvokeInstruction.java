package dev.xdark.classfile.representation.instruction;

import dev.xdark.classfile.representation.instruction.impl.InvokeInstructionImpl;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;

public interface InvokeInstruction extends MemberInstruction {

	@Override
	MethodType type();

	Mode mode();

	boolean isInterface();

	static InvokeInstruction create(Mode mode, ObjectType owner, String name, MethodType type, boolean isInterface) {
		return new InvokeInstructionImpl(owner, name, type, mode, isInterface);
	}

	static Instruction invokeVirtual(ObjectType owner, String name, MethodType type) {
		return create(Mode.INVOKEVIRTUAL, owner, name, type, false);
	}

	static Instruction invokeSpecial(ObjectType owner, String name, MethodType type, boolean isInterface) {
		return create(Mode.INVOKESPECIAL, owner, name, type, isInterface);
	}

	static Instruction invokeStatic(ObjectType owner, String name, MethodType type, boolean isInterface) {
		return create(Mode.INVOKESTATIC, owner, name, type, isInterface);
	}

	static Instruction invokeInterface(ObjectType owner, String name, MethodType type) {
		return create(Mode.INVOKEINTERFACE, owner, name, type, true);
	}

	enum Mode {
		INVOKEVIRTUAL,
		INVOKESPECIAL,
		INVOKESTATIC,
		INVOKEINTERFACE,
	}
}
