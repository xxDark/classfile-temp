package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.klass.module.impl.ProvideImpl;
import dev.xdark.classfile.io.Codec;

public interface Provide {
	Codec<Provide> CODEC = ProvideImpl.codec();

	int serviceIndex();

	int[] implementationIndices();
}
