package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.model.MemberModel;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.type.Type;

import java.util.List;

abstract class MemberModelImpl extends AttributedImpl implements MemberModel {
	private final int accessFlags;
	private final String name;
	private final Type type;
	private final String signature;

	MemberModelImpl(int accessFlags, String name, Type type, String signature, List<UnrecognizedAttribute> unrecognizedAttributes) {
		super(unrecognizedAttributes);
		this.accessFlags = accessFlags;
		this.name = name;
		this.type = type;
		this.signature = signature;
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
}
