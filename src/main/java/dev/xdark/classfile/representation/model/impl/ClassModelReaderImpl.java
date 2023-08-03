package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.model.ClassModel;
import dev.xdark.classfile.representation.model.ClassModelReader;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;

import java.util.List;

public final class ClassModelReaderImpl implements ClassModelReader {
	private final ClassModelImpl classModel = new ClassModelImpl();

	@Override
	public void visitVersion(ClassFileVersion version) {
		classModel.version = version;
	}

	@Override
	public void visitSymbolTable(SymbolTable symbolTable) {
	}

	@Override
	public void visitHeader(int accessFlags, InstanceType thisClass, InstanceType superClass, List<InstanceType> interfaces) {
		ClassModelImpl cm = classModel;
		cm.accessFlags = accessFlags;
		cm.type = thisClass;
		cm.superType = superClass;
		cm.interfaces = interfaces;
	}

	@Override
	public FieldVisitor visitField(int accessFlags, String name, ClassType type) {
		FieldModelImpl fieldModel = new FieldModelImpl(accessFlags, name, type);
		classModel.fields.add(fieldModel);
		return new FieldVisitorImpl(fieldModel);
	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, String name, MethodType type) {
		MethodModelImpl methodModel = new MethodModelImpl(accessFlags, name, type);
		classModel.methods.add(methodModel);
		return new MethodVisitorImpl(methodModel);
	}

	@Override
	public void visitSignature(String signature) {
		classModel.signature = signature;
	}

	@Override
	public ClassModel visitEnd() {
		return classModel;
	}

	@Override
	public AnnotationsVisitor visitVisibleRuntimeAnnotations() {
		return new AnnotationsCollector(classModel.visibleRuntimeAnnotations());
	}

	@Override
	public AnnotationsVisitor visitInvisibleRuntimeAnnotations() {
		return new AnnotationsCollector(classModel.invisibleRuntimeAnnotations());
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return classModel.unrecognizedAttributes()::add;
	}
}
