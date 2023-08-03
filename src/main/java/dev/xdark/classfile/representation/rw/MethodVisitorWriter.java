package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;

import java.util.Objects;

public final class MethodVisitorWriter extends MemberVisitorWriter implements MethodVisitor {
	private CodeVisitorWriter codeVisitorWriter;

	public MethodVisitorWriter(MutableSymbolTable symbolTable, dev.xdark.classfile.MethodVisitor visitor) {
		super(symbolTable, visitor);
	}

	@Override
	public CodeVisitor visitCode() {
		AttributesVisitor av = visitor.visitAttributes();
		if (av == null) {
			return null;
		}
		CodeVisitorWriter writer = codeVisitorWriter;
		if (writer == null) {
			writer = new CodeVisitorWriter(symtab);
			codeVisitorWriter = writer;
		}
		return writer;
	}

	@Override
	public void close() {
		super.close();
		CodeVisitorWriter writer = codeVisitorWriter;
		if (writer != null) {
			CodeAttribute compiled = writer.compile();
			AttributesVisitor av = visitor.visitAttributes();
			Objects.requireNonNull(av);
			av.visit(symtab.constantPool().add(ConstantUtf8.create(compiled.info().name())), compiled);
		}
	}
}
