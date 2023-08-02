package dev.xdark.classfile.impl;

import dev.xdark.classfile.ClassAssembler;
import dev.xdark.classfile.ClassFile;
import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.FieldVisitor;
import dev.xdark.classfile.MethodVisitor;
import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.attribute.AttributeWriter;
import dev.xdark.classfile.constantpool.Constant;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.BasicConstantPool;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.constantpool.SharingConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.BinaryOutput;
import dev.xdark.classfile.io.ClassWriter;
import dev.xdark.classfile.io.Codec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ClassAssemblerImpl extends AttributableImpl implements ClassAssembler {
	private final List<MethodVisitorImpl> methods = new ArrayList<>();
	private final List<FieldVisitorImpl> fields = new ArrayList<>();
	private final AttributeMapper attributeMapper;
	private final MutableConstantPool constantPool;
	private ClassFileVersion version;
	private int accessFlags;
	private int thisClass;
	private int superClass;
	private int[] interfaces;

	public ClassAssemblerImpl(ConstantPool constantPool, AttributeMapper attributeMapper) {
		this.attributeMapper = attributeMapper;
		MutableConstantPool mutable;
		if (constantPool == null) {
			mutable = new BasicConstantPool();
		} else {
			mutable = new BasicConstantPool();
			mutable.addAll(constantPool);
			mutable = new SharingConstantPool(mutable);
		}
		this.constantPool = mutable;
	}

	@Override
	public void visitVersion(ClassFileVersion version) {
		this.version = version;
	}

	@Override
	public MutableConstantPool visitConstantPool() {
		return constantPool;
	}

	@Override
	public void visitHeader(int accessFlags, int thisClass, int superClass, int[] interfaces) {
		this.accessFlags = accessFlags;
		this.thisClass = thisClass;
		this.superClass = superClass;
		this.interfaces = interfaces;
	}

	@Override
	public MethodVisitor visitMethod(int accessFlags, int nameIndex, int descriptorIndex) {
		MethodVisitorImpl method = new MethodVisitorImpl(accessFlags, nameIndex, descriptorIndex);
		methods.add(method);
		return method;
	}

	@Override
	public FieldVisitor visitField(int accessFlags, int nameIndex, int descriptorIndex) {
		FieldVisitorImpl field = new FieldVisitorImpl(accessFlags, nameIndex, descriptorIndex);
		fields.add(field);
		return field;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void dump(BinaryOutput output) throws IOException {
		MutableConstantPool cp = constantPool;
		ClassWriter writer = new ClassWriterImpl(output, version, cp, attributeMapper);
		writer.writeInt(ClassFile.MAGIC);
		ClassFileVersion.CODEC.write(writer, version);
		writer.writeShort(cp.size());
		for (Constant constant : cp) {
			Tag<?> tag = constant.tag();
			writer.writeConstantPoolTag(tag);
			((Codec) tag.codec()).write(writer, constant);
		}
		writer.writeAccessFlags(accessFlags);
		writer.writeConstantPoolIndex(thisClass);
		writer.writeConstantPoolIndex(superClass);
		int[] interfaces = this.interfaces;
		writer.writeShort(interfaces.length);
		for (int iface : interfaces) {
			writer.writeConstantPoolIndex(iface);
		}
		writeMembers(writer, fields);
		writeMembers(writer, methods);
		AttributeWriter.writeAll(writer, attributes);
	}

	private static void writeMembers(ClassWriter writer, List<? extends MemberVisitorImpl> list) throws IOException {
		writer.writeShort(list.size());
		for (MemberVisitorImpl member : list) {
			writer.writeAccessFlags(member.accessFlags);
			writer.writeConstantPoolIndex(member.nameIndex);
			writer.writeConstantPoolIndex(member.descriptorIndex);
			AttributeWriter.writeAll(writer, member.attributes);
		}
	}
}
