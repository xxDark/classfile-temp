package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.attribute.shared.SignatureAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeAnnotationsAttribute;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.UnrecognizedAttribute;

import java.io.IOException;

final class ClassAttributeCollector implements AttributesVisitor {
	private final ClassVisitorReader reader;

	ClassAttributeCollector(ClassVisitorReader reader) {
		this.reader = reader;
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		if (attribute instanceof BootstrapMethodsAttribute) {
			reader.bootstrapMethodsAttribute = (BootstrapMethodsAttribute) attribute;
		} else if (attribute instanceof SignatureAttribute) {
			ClassVisitorReader reader = this.reader;
			reader.cv.visitSignature(reader.constantPool.get(((SignatureAttribute) attribute).signatureIndex(), Tag.Utf8).value());
		} else if (attribute instanceof RuntimeAnnotationsAttribute) {
			ClassVisitorReader reader = this.reader;
			VisitorHelper.readAnnotations(reader.constantPool, reader.cv, (RuntimeAnnotationsAttribute) attribute);
		}
	}

	@Override
	public void visit(int nameIndex, UnknownAttribute attribute) throws IOException {
		ClassVisitorReader reader = this.reader;
		dev.xdark.classfile.representation.AttributesVisitor attributes = reader.cv.visitAttributes();
		if (attributes != null) {
			attributes.visit(UnrecognizedAttribute.create(reader.constantPool.get(nameIndex, Tag.Utf8).value(), attribute.payload()));
		}
	}
}
