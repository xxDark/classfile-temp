package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.attribute.method.AnnotationDefaultAttribute;
import dev.xdark.classfile.attribute.method.MethodParametersAttribute;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.GeneralAnnotationValueSink;
import dev.xdark.classfile.representation.annotation.impl.ValueInternal;
import dev.xdark.classfile.representation.method.MethodParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MethodVisitorWriter extends MemberVisitorWriter implements MethodVisitor {
	private List<MethodParameter> parameters;
	private CodeVisitorWriter codeVisitorWriter;
	private Element annotationDefault;

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
	public AnnotationValueSink annotationDefaultSink() {
		return new GeneralAnnotationValueSink() {
			@Override
			protected void accept(AnnotationValue value) {
				annotationDefault = ((ValueInternal) value).denormalize(symtab.constantPool());
			}
		};
	}

	@Override
	public void visitParameter(MethodParameter parameter) {
		List<MethodParameter> parameters = this.parameters;
		if (parameters == null) {
			parameters = new ArrayList<>(4);
			this.parameters = parameters;
		}
		parameters.add(parameter);
	}

	@Override
	public void close() {
		super.close();
		AttributesVisitor av = visitor.visitAttributes();
		CodeVisitorWriter writer = codeVisitorWriter;
		if (writer != null) {
			Objects.requireNonNull(av);
			CodeAttribute compiled = writer.compile();
			av.visit(symtab.constantPool().add(ConstantUtf8.create(compiled.info().name())), compiled);
		}
		Element annotationDefault = this.annotationDefault;
		if (annotationDefault != null) {
			Objects.requireNonNull(av);
			AnnotationDefaultAttribute attribute = AnnotationDefaultAttribute.create(annotationDefault);
			av.visit(symtab.constantPool().add(ConstantUtf8.create(attribute.info().name())), attribute);
		}
		List<MethodParameter> parameters = this.parameters;
		if (!parameters.isEmpty()) {
			Objects.requireNonNull(av);
			MutableConstantPool constantPool = symtab.constantPool();
			List<dev.xdark.classfile.attribute.method.MethodParameter> assembled = new ArrayList<>(parameters.size());
			for (MethodParameter parameter : parameters) {
				assembled.add(dev.xdark.classfile.attribute.method.MethodParameter.create(constantPool.add(ConstantUtf8.create(parameter.name())), parameter.accessFlags()));
			}
			MethodParametersAttribute attribute = MethodParametersAttribute.create(assembled);
			av.visit(constantPool.add(ConstantUtf8.create(attribute.info().name())), attribute);
		}
	}
}
