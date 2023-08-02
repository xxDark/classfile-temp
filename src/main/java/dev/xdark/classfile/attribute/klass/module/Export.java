package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.klass.module.impl.ExportImpl;
import dev.xdark.classfile.io.Codec;

public interface Export {
	Codec<Export> CODEC = ExportImpl.codec();

	int packageIndex();

	int flags();

	int[] moduleIndices();
}
