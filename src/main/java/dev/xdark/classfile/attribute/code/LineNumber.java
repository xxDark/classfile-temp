package dev.xdark.classfile.attribute.code;

import dev.xdark.classfile.attribute.code.impl.LineNumberImpl;
import dev.xdark.classfile.io.Codec;

public interface LineNumber {
	Codec<LineNumber> CODEC = LineNumberImpl.codec();

	int start();

	int lineNumber();

	static LineNumber create(int start, int lineNumber) {
		return new LineNumberImpl(start, lineNumber);
	}
}
