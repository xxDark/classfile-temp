package dev.xdark.classfile.representation.entity.indy.impl;

import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.ReferenceKind;
import dev.xdark.classfile.type.Type;

public final class MethodHandleImpl implements MethodHandle {
	private final ReferenceKind kind;
	private final ObjectType owner;
	private final String name;
	private final Type type;
	private final boolean isInterface;

	public MethodHandleImpl(ReferenceKind kind, ObjectType owner, String name, Type type, boolean isInterface) {
		this.kind = kind;
		this.owner = owner;
		this.name = name;
		this.type = type;
		this.isInterface = isInterface;
	}

	@Override
	public ReferenceKind kind() {
		return kind;
	}

	@Override
	public ObjectType owner() {
		return owner;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public boolean isInterface() {
		return isInterface;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MethodHandleImpl that = (MethodHandleImpl) o;

		if (isInterface != that.isInterface) return false;
		if (kind != that.kind) return false;
		if (!owner.equals(that.owner)) return false;
		if (!name.equals(that.name)) return false;
		return type.equals(that.type);
	}

	@Override
	public int hashCode() {
		int result = kind.hashCode();
		result = 31 * result + owner.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + (isInterface ? 1 : 0);
		return result;
	}
}
