package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.Attributed;
import dev.xdark.classfile.representation.UnrecognizedAttribute;

import java.util.List;

abstract class AttributedImpl implements Attributed {
	private final List<UnrecognizedAttribute> unrecognizedAttributes;

	protected AttributedImpl(List<UnrecognizedAttribute> unrecognizedAttributes) {
		this.unrecognizedAttributes = unrecognizedAttributes;
	}

	@Override
	public List<UnrecognizedAttribute> unrecognizedAttributes() {
		return unrecognizedAttributes;
	}
}
