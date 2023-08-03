package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.MemberVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;

abstract class MemberVisitorWriter extends AnnotatableWriter implements MemberVisitor {
	protected final MutableSymbolTable symtab;
	protected final dev.xdark.classfile.MemberVisitor visitor;

	MemberVisitorWriter(MutableSymbolTable symtab, dev.xdark.classfile.MemberVisitor visitor) {
		super(symtab.constantPool(), visitor);
		this.symtab = symtab;
		this.visitor = visitor;
	}

	@Override
	public final void visitSignature(String signature) {
		VisitorHelper.writeSignature(symtab.constantPool(), visitor.visitAttributes(), signature);
	}

	@Override
	public final AttributesVisitor visitAttributes() {
		dev.xdark.classfile.attribute.AttributesVisitor av = visitor.visitAttributes();
		return av == null ? null : new AttributesVisitorWriter(symtab.constantPool(), av);
	}
}
