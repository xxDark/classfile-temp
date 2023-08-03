package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.MemberVisitor;
import dev.xdark.classfile.attribute.Attributable;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.attribute.shared.SignatureAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeAnnotationsAttribute;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.UnrecognizedAttribute;

import java.io.IOException;

class MemberVisitorReader implements MemberVisitor, Attributable, AttributesVisitor {
	protected final dev.xdark.classfile.representation.MemberVisitor mv;
	protected final SymbolTable symbolTable;

	MemberVisitorReader(dev.xdark.classfile.representation.MemberVisitor mv, SymbolTable symbolTable) {
		this.mv = mv;
		this.symbolTable = symbolTable;
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		if (attribute instanceof SignatureAttribute) {
			mv.visitSignature(symbolTable.constantPool().get(((SignatureAttribute) attribute).signatureIndex(), Tag.Utf8).value());
		} else if (attribute instanceof RuntimeAnnotationsAttribute) {
			VisitorHelper.readAnnotations(symbolTable.constantPool(), mv, (RuntimeAnnotationsAttribute) attribute);
		}
	}

	@Override
	public final void visit(int nameIndex, UnknownAttribute attribute) throws IOException {
		dev.xdark.classfile.representation.AttributesVisitor av = mv.visitAttributes();
		if (av != null) {
			av.visit(UnrecognizedAttribute.create(symbolTable.constantPool().get(nameIndex, Tag.Utf8).value(), attribute.payload()));
		}
	}

	@Override
	public final AttributesVisitor visitAttributes() {
		return this;
	}
}
