package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.Annotatable;
import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.Attributable;
import dev.xdark.classfile.representation.Attributed;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.UnrecognizedAttribute;
import dev.xdark.classfile.representation.annotation.AnnotationContainer;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.model.Annotated;

import java.util.List;

final class ModelHelper {

	private ModelHelper() {
	}

	static void acceptAnnotations(AnnotationsVisitor visitor, List<AnnotationContainer> annotations) {
		if (visitor == null) {
			return;
		}
		for (AnnotationContainer annotation : annotations) {
			AnnotationContainerVisitor av = visitor.visitAnnotation(annotation.type());
			if (av != null) {
				annotation.accept(av);
			}
		}
	}

	static void acceptAttributes(AttributesVisitor visitor, List<UnrecognizedAttribute> attributes) {
		if (visitor == null) {
			return;
		}
		for (UnrecognizedAttribute attribute : attributes) {
			visitor.visit(attribute);
		}
	}

	static <AV extends Attributable & Annotatable, M extends Attributed & Annotated> void accept(AV visitor, M member) {
		acceptAnnotations(visitor.visitVisibleRuntimeAnnotations(), member.visibleRuntimeAnnotations());
		acceptAnnotations(visitor.visitInvisibleRuntimeAnnotations(), member.invisibleRuntimeAnnotations());
		acceptAttributes(visitor.visitAttributes(), member.unrecognizedAttributes());
	}
}
