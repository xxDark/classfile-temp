package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.ObjectVariableInfoImpl;

public interface ObjectVariableInfo extends VerificationTypeInfo {

	int classIndex();

	@Override
	VerificationTypeDescriptor<ObjectVariableInfo> descriptor();

	static ObjectVariableInfo create(int classIndex) {
		return new ObjectVariableInfoImpl(classIndex);
	}
}
