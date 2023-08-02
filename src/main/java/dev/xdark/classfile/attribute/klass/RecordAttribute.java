package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

import java.util.List;

public interface RecordAttribute extends SpecAttribute {

	List<RecordComponent> components();
	@Override
	AttributeInfo<RecordAttribute> info();
}
