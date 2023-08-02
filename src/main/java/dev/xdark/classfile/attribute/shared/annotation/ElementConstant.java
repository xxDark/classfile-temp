package dev.xdark.classfile.attribute.shared.annotation;

public interface ElementConstant extends Element {

	int constantIndex();

	@Override
	ElementDescriptor<? extends ElementConstant> descriptor();
}
