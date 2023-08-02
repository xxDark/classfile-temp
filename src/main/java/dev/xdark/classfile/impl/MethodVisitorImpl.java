package dev.xdark.classfile.impl;

import dev.xdark.classfile.MethodVisitor;

final class MethodVisitorImpl extends MemberVisitorImpl implements MethodVisitor {

	MethodVisitorImpl(int accessFlags, int nameIndex, int descriptorIndex) {
		super(accessFlags, nameIndex, descriptorIndex);
	}
}
