package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.klass.module.impl.RequireImpl;
import dev.xdark.classfile.io.Codec;

public interface Require {
	Codec<Require> CODEC = RequireImpl.codec();

	int moduleIndex();

	int flags();

	int versionIndex();
}
