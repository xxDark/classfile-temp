package dev.xdark.classfile.attribute.klass.module;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;

import java.util.List;

public interface ModuleAttribute extends SpecAttribute {

	int nameIndex();

	int flags();

	int versionIndex();

	List<Require> requires();

	List<Export> exports();

	List<Open> opens();

	int[] uses();

	List<Provide> provides();

	@Override
	AttributeInfo<ModuleAttribute> info();
}
