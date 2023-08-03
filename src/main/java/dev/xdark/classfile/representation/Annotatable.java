package dev.xdark.classfile.representation;

public interface Annotatable {

	AnnotationVisitor visitVisibleAnnotations();

	AnnotationVisitor visitInvisibleAnnotations();
}
