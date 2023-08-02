package dev.xdark.classfile.impl;

import dev.xdark.classfile.FieldVisitor;

final class FieldVisitorImpl extends MemberVisitorImpl implements FieldVisitor {

	FieldVisitorImpl(int accessFlags, int nameIndex, int descriptorIndex) {
		super(accessFlags, nameIndex, descriptorIndex);
	}
}
