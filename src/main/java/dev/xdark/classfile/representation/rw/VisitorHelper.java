package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.shared.SignatureAttribute;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeVisibleAnnotationsAttribute;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.Annotatable;
import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.type.InstanceType;

import java.util.List;

final class VisitorHelper {
	private VisitorHelper() {
	}

	static void readAnnotations(ConstantPool constantPool, Annotatable annotatable, RuntimeAnnotationsAttribute attribute) {
		AnnotationsVisitor visitor;
		if (attribute instanceof RuntimeVisibleAnnotationsAttribute) {
			visitor = annotatable.visitVisibleRuntimeAnnotations();
		} else {
			visitor = annotatable.visitInvisibleRuntimeAnnotations();
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

	static void writeSignature(MutableConstantPool constantPool, AttributesVisitor visitor, String signature) {
		if (visitor == null) {
			return;
		}
		visitor.visit(constantPool.add(ConstantUtf8.create(AttributeInfo.Signature.name())), SignatureAttribute.create(signature == null ? 0 : constantPool.add(ConstantUtf8.create(signature))));
	}
}
