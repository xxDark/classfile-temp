package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.InvokeInstruction;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;

public final class InvokeInstructionImpl extends MemberInstructionImpl implements InvokeInstruction {
	private final Mode mode;
	private final boolean isInterface;

	public InvokeInstructionImpl(ObjectType owner, String name, MethodType type, Mode mode, boolean isInterface) {
		super(owner, name, type);
		this.mode = mode;
		this.isInterface = isInterface;
	}

	@Override
	public Mode mode() {
		return mode;
	}

	@Override
	public boolean isInterface() {
		return isInterface;
	}

	@Override
	public MethodType type() {
		return (MethodType) super.type();
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		ObjectType owner = this.owner;
		String name = this.name;
		MethodType type = (MethodType) this.type;
		boolean isInterface = this.isInterface;
		switch (mode) {
			case INVOKEVIRTUAL:
				visitor.invokeVirtual(owner, name, type);
				break;
			case INVOKESPECIAL:
				visitor.invokeSpecial(owner, name, type, isInterface);
				break;
			case INVOKESTATIC:
				visitor.invokeStatic(owner, name, type, isInterface);
				break;
			case INVOKEINTERFACE:
				visitor.invokeInterface(owner, name, type);
		}
	}
}
