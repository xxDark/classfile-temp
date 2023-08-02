package dev.xdark.classfile.attribute.code.stackmap.type;

public interface ObjectVariableInfo extends VerificationTypeInfo {

	int classIndex();

	@Override
	VerificationTypeDescriptor<ObjectVariableInfo> descriptor();
}
