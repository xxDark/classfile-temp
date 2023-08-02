package dev.xdark.classfile.attribute.method;

import dev.xdark.classfile.attribute.method.impl.MethodParameterImpl;
import dev.xdark.classfile.io.Codec;

public interface MethodParameter {
	Codec<MethodParameter> CODEC = MethodParameterImpl.codec();

	int nameIndex();

	int accessFlags();

	static MethodParameter create(int nameIndex, int accessFlags) {
		return new MethodParameterImpl(nameIndex, accessFlags);
	}
}
