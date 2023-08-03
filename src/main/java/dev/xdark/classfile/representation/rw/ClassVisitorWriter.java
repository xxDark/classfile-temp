package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.ConstantClass;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.ClassVisitor;
import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;

import java.util.ArrayList;
import java.util.List;

public final class ClassVisitorWriter extends AnnotatableWriter implements ClassVisitor, AutoCloseable {
	private final List<FieldVisitorWriter> fields = new ArrayList<>();
	private final List<MethodVisitorWriter> methods = new ArrayList<>();
	private final dev.xdark.classfile.ClassVisitor cv;
	private final MutableSymbolTable symbolTable;

	public ClassVisitorWriter(dev.xdark.classfile.ClassVisitor cv) {
		super(cv.visitConstantPool(), cv);
		this.cv = cv;
		symbolTable = MutableSymbolTable.create(cv.visitConstantPool(), BootstrapMethodsAttribute.create(new ArrayList<>()));
	}

	@Override
	public void visitVersion(ClassFileVersion version) {
		cv.visitVersion(version);
	}

	@Override
	public void visitSymbolTable(SymbolTable symbolTable) {
		// Ignore, cannot trust this symbol table
	}

	@Override
	public void visitHeader(int accessFlags, InstanceType thisClass, InstanceType superClass, List<InstanceType> interfaces) {
		dev.xdark.classfile.ClassVisitor cv = this.cv;
		MutableConstantPool constantPool = cv.visitConstantPool();
		int[] interfaceIndices = new int[interfaces.size()];
		int i = 0;
		for (InstanceType anInterface : interfaces) {
			interfaceIndices[i++] = addClass(constantPool, anInterface);
		}
		cv.visitHeader(
				accessFlags,
				addClass(constantPool, thisClass),
				addClass(constantPool, superClass),
				interfaceIndices
		);
	}

	@Override
	public FieldVisitor visitField(int accessFlags, String name, ClassType type) {
		MutableSymbolTable symtab = symbolTable;
		MutableConstantPool constantPool = symtab.constantPool();
		dev.xdark.classfile.FieldVisitor fv = cv.visitField(
				accessFlags,
				constantPool.add(ConstantUtf8.create(name)),
				constantPool.add(ConstantUtf8.create(type.descriptor()))
		);
		if (fv == null) {
			return null;
		}
		FieldVisitorWriter writer = new FieldVisitorWriter(symtab, fv);
		fields.add(writer);
		return writer;
	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, String name, MethodType type) {
		MutableSymbolTable symtab = symbolTable;
		MutableConstantPool constantPool = symtab.constantPool();
		dev.xdark.classfile.MethodVisitor mv = cv.visitMethod(
				accessFlags,
				constantPool.add(ConstantUtf8.create(name)),
				constantPool.add(ConstantUtf8.create(type.descriptor()))
		);
		if (mv == null) {
			return null;
		}
		MethodVisitorWriter writer = new MethodVisitorWriter(symtab, mv);
		methods.add(writer);
		return writer;
	}

	@Override
	public void visitSignature(String signature) {
		VisitorHelper.writeSignature(symbolTable.constantPool(), cv.visitAttributes(), signature);
	}

	@Override
	public AttributesVisitor visitAttributes() {
		dev.xdark.classfile.attribute.AttributesVisitor visitor = cv.visitAttributes();
		return visitor == null ? null : new AttributesVisitorWriter(symbolTable.constantPool(), visitor);
	}

	@Override
	public void close() {
		super.close();
		for (FieldVisitorWriter field : fields) {
			field.close();
		}
		for (MethodVisitorWriter method : methods) {
			method.close();
		}
		dev.xdark.classfile.attribute.AttributesVisitor attrs = cv.visitAttributes();
		if (attrs == null) {
			return;
		}
		MutableSymbolTable symtab = symbolTable;
		BootstrapMethodsAttribute attr = symtab.bootstrapMethodsAttribute();
		if (!attr.bootstrapMethods().isEmpty()) {
			attrs.visit(symtab.constantPool().add(ConstantUtf8.create(attr.info().name())), attr);
		}
	}

	private static int addClass(MutableConstantPool constantPool, InstanceType type) {
		return type == null ? 0 : constantPool.add(ConstantClass.create(constantPool.add(ConstantUtf8.create(type.internalName()))));
	}
}
