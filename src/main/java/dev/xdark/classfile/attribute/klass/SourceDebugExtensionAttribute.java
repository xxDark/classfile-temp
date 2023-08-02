package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.klass.impl.SourceDebugExtensionAttributeImpl;
import dev.xdark.classfile.io.BinaryInput;

import java.io.IOException;

public interface SourceDebugExtensionAttribute extends SpecAttribute {

	BinaryInput payload() throws IOException;

	@Override
	AttributeInfo<SourceDebugExtensionAttribute> info();

	static SourceDebugExtensionAttribute create(BinaryInput input) {
		return new SourceDebugExtensionAttributeImpl(input);
	}
}
