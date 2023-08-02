package dev.xdark.classfile.attribute.code.stackmap.type;

public interface UninitializedVariableInfo extends VerificationTypeInfo {

	int offset();

	@Override
	VerificationTypeDescriptor<UninitializedVariableInfo> descriptor();
}
