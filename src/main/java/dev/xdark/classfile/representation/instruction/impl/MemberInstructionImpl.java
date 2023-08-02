package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.instruction.MemberInstruction;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.Type;

abstract class MemberInstructionImpl implements MemberInstruction {
	protected final ObjectType owner;
	protected final String name;
	protected final Type type;

	MemberInstructionImpl(ObjectType owner, String name, Type type) {
		this.owner = owner;
		this.name = name;
		this.type = type;
	}

	@Override
	public ObjectType owner() {
		return owner;
	}

	@Override
	public final String name() {
		return name;
	}

	@Override
	public Type type() {
		return type;
	}
}
