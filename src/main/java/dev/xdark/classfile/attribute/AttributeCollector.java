package dev.xdark.classfile.attribute;

import java.io.IOException;
import java.util.function.Consumer;

public final class AttributeCollector implements AttributesVisitor {
	private final Consumer<IndexedAttribute> consumer;

	public AttributeCollector(Consumer<IndexedAttribute> consumer) {
		this.consumer = consumer;
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		consumer.accept(new IndexedAttribute(nameIndex, attribute));
	}

	@Override
	public void visit(int nameIndex, UnknownAttribute attribute) {
		consumer.accept(new IndexedAttribute(nameIndex, attribute));
	}
}
