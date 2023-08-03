package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.representation.UnrecognizedAttribute;

public final class UnrecognizedAttributeImpl implements UnrecognizedAttribute {
	private final String name;
	private final BinaryInput payload;

	public UnrecognizedAttributeImpl(String name, BinaryInput payload) {
		this.name = name;
		this.payload = payload;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public BinaryInput payload() {
		return payload.duplicate();
	}
}
