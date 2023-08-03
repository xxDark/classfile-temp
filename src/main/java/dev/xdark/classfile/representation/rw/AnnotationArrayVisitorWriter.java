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

public final class AnnotationArrayVisitorWriter implements ValueArrayVisitor {
	private final MutableConstantPool constantPool;
	private final ArrayVisitor visitor;

	public AnnotationArrayVisitorWriter(MutableConstantPool constantPool, ArrayVisitor visitor) {
		this.constantPool = constantPool;
		this.visitor = visitor;
	}

	@Override
	public void visit(AnnotationValue value) {
		visitor.visit(((ValueInternal) value).denormalize(constantPool));
	}

	@Override
	public AnnotationContainerVisitor visitAnnotation(InstanceType type) {
		MutableConstantPool constantPool = this.constantPool;
		AnnotationVisitor visitor = this.visitor.visitAnnotation(constantPool.add(ConstantUtf8.create(type.descriptor())));
		return visitor == null ? null : new AnnotationVisitorWriter(constantPool, visitor);
	}

	@Override
	public ValueArrayVisitor visitArray() {
		ArrayVisitor visitor = this.visitor.visitArray();
		return visitor == null ? null : new AnnotationArrayVisitorWriter(constantPool, visitor);
	}

	@Override
	public void visitEnd() {
		visitor.visitEnd();
	}
}
