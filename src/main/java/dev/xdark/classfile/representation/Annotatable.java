package dev.xdark.classfile.representation;

public interface Annotatable {

	AnnotationsVisitor visitVisibleRuntimeAnnotations();

	AnnotationsVisitor visitInvisibleRuntimeAnnotations();
}
