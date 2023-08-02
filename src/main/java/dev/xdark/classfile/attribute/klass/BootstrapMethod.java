package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.klass.impl.BootstrapMethodImpl;
import dev.xdark.classfile.io.Codec;

public interface BootstrapMethod {
	Codec<BootstrapMethod> CODEC = BootstrapMethodImpl.codec();

	int referenceIndex();

	int[] argumentIndices();

	static BootstrapMethod create(int referenceIndex, int[] argumentIndices) {
		return new BootstrapMethodImpl(referenceIndex, argumentIndices);
	}
}
