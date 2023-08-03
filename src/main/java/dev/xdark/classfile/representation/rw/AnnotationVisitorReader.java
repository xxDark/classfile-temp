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

public final class AnnotationVisitorReader implements AnnotationVisitor {
	private final ConstantPool constantPool;
	private final AnnotationContainerVisitor av;

	public AnnotationVisitorReader(ConstantPool constantPool, AnnotationContainerVisitor av) {
		this.constantPool = constantPool;
		this.av = av;
	}

	@Override
	public void visit(int nameIndex, Element element) {
		ConstantPool constantPool = this.constantPool;
		av.visit(constantPool.get(nameIndex, Tag.Utf8).value(), ((ElementInternal) element).normalise(constantPool));
	}

	@Override
	public AnnotationVisitor visitAnnotation(int nameIndex, int typeIndex) {
		ConstantPool constantPool = this.constantPool;
		AnnotationContainerVisitor visitor = av.visitAnnotation(
				constantPool.get(nameIndex, Tag.Utf8).value(),
				// Why is it not an internal name, again?...
				InstanceType.ofDescriptor(constantPool.get(typeIndex, Tag.Utf8).value())
		);
		return visitor == null ? null : new AnnotationVisitorReader(constantPool, visitor);
	}

	@Override
	public ArrayVisitor visitArray(int nameIndex) {
		ConstantPool constantPool = this.constantPool;
		ValueArrayVisitor visitor = av.visitArray(constantPool.get(nameIndex, Tag.Utf8).value());
		return visitor == null ? null : new AnnotationArrayVisitorReader(constantPool, visitor);
	}

	@Override
	public void visitEnd() {
		av.visitEnd();
	}
}
