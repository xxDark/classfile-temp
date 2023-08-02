package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.klass.module.impl.OpenImpl;
import dev.xdark.classfile.io.Codec;

public interface Open {
	Codec<Open> CODEC = OpenImpl.codec();

	int packageIndex();

	int flags();

	int[] moduleIndices();
}
