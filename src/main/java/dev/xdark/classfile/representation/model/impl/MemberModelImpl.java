package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.MemberVisitor;
import dev.xdark.classfile.representation.model.MemberModel;
import dev.xdark.classfile.type.Type;

import java.util.ArrayList;

abstract class MemberModelImpl extends BaseImpl implements MemberModel {
	int accessFlags;
	String name;
	Type type;
	String signature;

	MemberModelImpl(
			int accessFlags,
			String name,
			Type type
	) {
		super(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		this.accessFlags = accessFlags;
		this.name = name;
		this.type = type;
	}

	@Override
	public final int accessFlags() {
		return accessFlags;
	}

	@Override
	public final String name() {
		return name;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public final String signature() {
		return signature;
	}

	protected final void accept(MemberVisitor visitor) {
		visitor.visitSignature(signature);
		ModelHelper.accept(visitor, this);
	}
}
