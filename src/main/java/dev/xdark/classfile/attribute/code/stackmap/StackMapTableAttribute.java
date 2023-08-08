package dev.xdark.classfile.attribute.code.stackmap;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.code.stackmap.frame.StackMapFrame;

import java.util.List;

public interface StackMapTableAttribute extends SpecAttribute {

	List<StackMapFrame> frames();

	@Override
	AttributeInfo<StackMapTableAttribute> info();
}
