package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.method.MethodParameter;

public interface MethodVisitor extends MemberVisitor {

	CodeVisitor visitCode();

	AnnotationValueSink annotationDefaultSink();

	void visitParameter(MethodParameter parameter);
}
