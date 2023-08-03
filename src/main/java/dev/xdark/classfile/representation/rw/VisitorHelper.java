package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeVisibleAnnotationsAttribute;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.Annotatable;
import dev.xdark.classfile.representation.AnnotationVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.type.InstanceType;

import java.util.List;

final class VisitorHelper {
	private VisitorHelper() {
	}

	static void visitAnnotations(ConstantPool constantPool, Annotatable annotatable, RuntimeAnnotationsAttribute attribute) {
		AnnotationVisitor visitor;
		if (attribute instanceof RuntimeVisibleAnnotationsAttribute) {
			visitor = annotatable.visitVisibleAnnotations();
		} else {
			visitor = annotatable.visitInvisibleAnnotations();
		}
		if (visitor != null) {
			List<ElementAnnotation> annotations = attribute.annotations();
			for (ElementAnnotation annotation : annotations) {
				AnnotationContainerVisitor av = visitor.visitAnnotation(InstanceType.ofDescriptor(constantPool.get(annotation.typeIndex(), Tag.Utf8).value()));
				if (av != null) {
					annotation.accept(new AnnotationVisitorReader(constantPool, av));
				}
			}
		}
	}
}
