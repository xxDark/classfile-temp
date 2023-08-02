package dev.xdark.classfile.impl;

import dev.xdark.classfile.attribute.Attributable;
import dev.xdark.classfile.attribute.AttributeCollector;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.IndexedAttribute;

import java.util.ArrayList;
import java.util.List;

class AttributableImpl implements Attributable {
	final List<IndexedAttribute> attributes;
	private final AttributesVisitor attributesVisitor;

	AttributableImpl() {
		List<IndexedAttribute> attributes = new ArrayList<>();
		this.attributes = attributes;
		attributesVisitor = new AttributeCollector(attributes::add);
	}

	@Override
	public final AttributesVisitor visitAttributes() {
		return attributesVisitor;
	}

}
