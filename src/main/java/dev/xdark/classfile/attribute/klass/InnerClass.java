package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.klass.impl.InnerClassImpl;
import dev.xdark.classfile.io.Codec;

public interface InnerClass {
	Codec<InnerClass> CODEC = InnerClassImpl.codec();

	int innerClassInfoIndex();

	int outerClassInfoIndex();

	int innerNameIndex();

	int innerClassAccessFlags();
}
