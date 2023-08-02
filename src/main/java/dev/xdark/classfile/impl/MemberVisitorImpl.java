package dev.xdark.classfile.impl;

abstract class MemberVisitorImpl extends AttributableImpl {
	final int accessFlags;
	final int nameIndex;
	final int descriptorIndex;

	MemberVisitorImpl(int accessFlags, int nameIndex, int descriptorIndex) {
		this.accessFlags = accessFlags;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}
}
