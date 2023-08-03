package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.shared.annotation.AnnotationVisitor;
import dev.xdark.classfile.attribute.shared.annotation.ArrayVisitor;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueArrayVisitor;
import dev.xdark.classfile.representation.annotation.impl.ValueInternal;
import dev.xdark.classfile.type.InstanceType;

public final class AnnotationVisitorWriter implements AnnotationContainerVisitor {
	private final MutableConstantPool constantPool;
	private final AnnotationVisitor visitor;

	public AnnotationVisitorWriter(MutableConstantPool constantPool, AnnotationVisitor visitor) {
		this.constantPool = constantPool;
		this.visitor = visitor;
	}

	@Override
	public void visit(String name, AnnotationValue value) {
		MutableConstantPool constantPool = this.constantPool;
		visitor.visit(constantPool.add(ConstantUtf8.create(name)), ((ValueInternal) value).denormalize(constantPool));
	}

	@Override
	public AnnotationContainerVisitor visitAnnotation(String name, InstanceType type) {
		MutableConstantPool constantPool = this.constantPool;
		AnnotationVisitor visitor = this.visitor.visitAnnotation(
				constantPool.add(ConstantUtf8.create(name)),
				constantPool.add(ConstantUtf8.create(type.descriptor()))
		);
		return visitor == null ? null : new AnnotationVisitorWriter(constantPool, visitor);
	}

	@Override
	public ValueArrayVisitor visitArray(String name) {
		MutableConstantPool constantPool = this.constantPool;
		ArrayVisitor visitor = this.visitor.visitArray(constantPool.add(ConstantUtf8.create(name)));
		return visitor == null ? null : new AnnotationArrayVisitorWriter(constantPool, visitor);
	}
}
