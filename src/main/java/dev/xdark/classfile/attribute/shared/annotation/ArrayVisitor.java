package dev.xdark.classfile.attribute.shared.annotation;

public interface ArrayVisitor {

	void visit(Element element);

	AnnotationVisitor visitAnnotation(int typeIndex);

	ArrayVisitor visitArray();

	void visitEnd();
}
