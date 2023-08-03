package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

import java.util.List;

public interface RuntimeAnnotationsAttribute extends SpecAttribute {

	List<ElementAnnotation> annotations();

	@Override
	AttributeInfo<? extends RuntimeAnnotationsAttribute> info();
}
