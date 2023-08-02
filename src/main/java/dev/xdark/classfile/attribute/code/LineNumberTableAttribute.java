package dev.xdark.classfile.attribute.code;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.code.impl.LineNumberTableAttributeImpl;

import java.util.List;

public interface LineNumberTableAttribute extends SpecAttribute {

	List<LineNumber> lineNumbers();

	@Override
	AttributeInfo<LineNumberTableAttribute> info();

	static LineNumberTableAttribute create(List<LineNumber> lineNumbers) {
		return new LineNumberTableAttributeImpl(lineNumbers);
	}
}
