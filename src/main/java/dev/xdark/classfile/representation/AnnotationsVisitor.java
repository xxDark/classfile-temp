package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.type.InstanceType;

public interface AnnotationsVisitor {

	AnnotationContainerVisitor visitAnnotation(InstanceType type);
}
