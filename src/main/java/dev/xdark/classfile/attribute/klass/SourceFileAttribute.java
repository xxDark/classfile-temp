package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

public interface SourceFileAttribute extends SpecAttribute {

	int sourceFileIndex();

	@Override
	AttributeInfo<SourceFileAttribute> info();
}
