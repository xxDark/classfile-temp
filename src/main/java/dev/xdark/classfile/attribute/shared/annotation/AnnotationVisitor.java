package dev.xdark.classfile.attribute.shared.annotation;

public interface AnnotationVisitor {

	void visit(int nameIndex, Element element);

	AnnotationVisitor visitAnnotation(int nameIndex, int typeIndex);

	ArrayVisitor visitArray(int nameIndex);

	void visitEnd();
}
