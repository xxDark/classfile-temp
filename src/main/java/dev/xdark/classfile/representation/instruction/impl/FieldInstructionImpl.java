package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.instruction.FieldInstruction;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.Type;

public final class FieldInstructionImpl extends MemberInstructionImpl implements FieldInstruction {
	private final Mode mode;

	public FieldInstructionImpl(ObjectType owner, String name, Type type, Mode mode) {
		super(owner, name, type);
		this.mode = mode;
	}

	@Override
	public Mode mode() {
		return mode;
	}

	@Override
	public InstanceType owner() {
		return (InstanceType) super.owner();
	}

	@Override
	public ClassType type() {
		return (ClassType) super.type();
	}

	@Override
	public void accept(BytecodeVisitor visitor) {
		InstanceType owner = (InstanceType) this.owner;
		String name = this.name;
		ClassType type = (ClassType) this.type;
		switch (mode) {
			case GETSTATIC:
				visitor.getStatic(owner, name, type);
				break;
			case PUTSTATIC:
				visitor.putStatic(owner, name, type);
				break;
			case GETFIELD:
				visitor.getField(owner, name, type);
				break;
			case PUTFIELD:
				visitor.putField(owner, name, type);
				break;
		}
	}
}
