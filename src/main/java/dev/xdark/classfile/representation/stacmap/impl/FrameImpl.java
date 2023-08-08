package dev.xdark.classfile.representation.stacmap.impl;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.stacmap.Frame;
import dev.xdark.classfile.representation.stacmap.FrameType;
import dev.xdark.classfile.representation.stacmap.VerificationTypeInfo;

import java.util.List;

public final class FrameImpl implements Frame {
	private final Label location;
	private final FrameType type;
	private final List<VerificationTypeInfo> stack;
	private final List<VerificationTypeInfo> locals;

	public FrameImpl(Label location, FrameType type, List<VerificationTypeInfo> stack, List<VerificationTypeInfo> locals) {
		this.location = location;
		this.type = type;
		this.stack = stack;
		this.locals = locals;
	}

	@Override
	public Label location() {
		return location;
	}

	@Override
	public FrameType type() {
		return type;
	}

	@Override
	public List<VerificationTypeInfo> stack() {
		return stack;
	}

	@Override
	public List<VerificationTypeInfo> locals() {
		return locals;
	}
}
