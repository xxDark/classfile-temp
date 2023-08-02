package dev.xdark.classfile.attribute.code;

import dev.xdark.classfile.attribute.code.impl.ExceptionTableEntryImpl;
import dev.xdark.classfile.io.Codec;

public interface ExceptionTableEntry {
	Codec<ExceptionTableEntry> CODEC = ExceptionTableEntryImpl.codec();

	int start();

	int end();

	int handler();

	int typeIndex();
}
