package dev.xdark.classfile.representation.annotation;

public interface AnnotationValue {

	void accept(AnnotationValueSink sink);
}
