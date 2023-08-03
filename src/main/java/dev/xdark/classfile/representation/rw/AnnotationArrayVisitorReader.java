package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.shared.annotation.AnnotationVisitor;
import dev.xdark.classfile.attribute.shared.annotation.ArrayVisitor;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementInternal;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.annotation.ValueArrayVisitor;
import dev.xdark.classfile.type.InstanceType;

public final class AnnotationArrayVisitorReader implements ArrayVisitor {
	private final ConstantPool constantPool;
	private final ValueArrayVisitor av;

	public AnnotationArrayVisitorReader(ConstantPool constantPool, ValueArrayVisitor av) {
		this.constantPool = constantPool;
		this.av = av;
	}

	@Override
	public void visit(Element element) {
		av.visit(((ElementInternal) element).normalise(constantPool));
	}

	@Override
	public AnnotationVisitor visitAnnotation(int typeIndex) {
		ConstantPool constantPool = this.constantPool;
		AnnotationContainerVisitor visitor = av.visitAnnotation(InstanceType.ofDescriptor(constantPool.get(typeIndex, Tag.Utf8).value()));
		return visitor == null ? null : new AnnotationVisitorReader(constantPool, visitor);
	}

	@Override
	public ArrayVisitor visitArray() {
		ValueArrayVisitor visitor = av.visitArray();
		return visitor == null ? null : new AnnotationArrayVisitorReader(constantPool, visitor);
	}
}
