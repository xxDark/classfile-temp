package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.UnrecognizedAttribute;

public final class AttributesVisitorWriter implements AttributesVisitor {
	private final MutableConstantPool constantPool;
	private final dev.xdark.classfile.attribute.AttributesVisitor visitor;

	public AttributesVisitorWriter(MutableConstantPool constantPool, dev.xdark.classfile.attribute.AttributesVisitor visitor) {
		this.constantPool = constantPool;
		this.visitor = visitor;
	}

	@Override
	public void visit(UnrecognizedAttribute attribute) {
		visitor.visit(
				constantPool.add(ConstantUtf8.create(attribute.name())),
				UnknownAttribute.create(attribute.payload())
		);
	}
}
