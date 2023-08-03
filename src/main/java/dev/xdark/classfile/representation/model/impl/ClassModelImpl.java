package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.representation.ClassVisitor;
import dev.xdark.classfile.representation.FieldVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.model.ClassModel;
import dev.xdark.classfile.representation.model.FieldModel;
import dev.xdark.classfile.representation.model.MethodModel;
import dev.xdark.classfile.type.InstanceType;

import java.util.ArrayList;
import java.util.List;

final class ClassModelImpl extends BaseImpl implements ClassModel {
	final List<FieldModel> fields = new ArrayList<>();
	final List<MethodModel> methods = new ArrayList<>();
	ClassFileVersion version;
	int accessFlags;
	InstanceType type;
	InstanceType superType;
	List<InstanceType> interfaces;
	String signature;

	ClassModelImpl() {
		super(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	@Override
	public ClassFileVersion version() {
		return version;
	}

	@Override
	public InstanceType type() {
		return type;
	}

	@Override
	public InstanceType superType() {
		return superType;
	}

	@Override
	public List<InstanceType> interfaces() {
		return interfaces;
	}

	@Override
	public List<MethodModel> methods() {
		return methods;
	}

	@Override
	public List<FieldModel> fields() {
		return fields;
	}

	@Override
	public int accessFlags() {
		return accessFlags;
	}

	@Override
	public String signature() {
		return signature;
	}

	@Override
	public void accept(ClassVisitor visitor) {
		visitor.visitVersion(version);
		visitor.visitHeader(accessFlags, type, superType, interfaces);
		for (FieldModel field : fields) {
			FieldVisitor fv = visitor.visitField(field.accessFlags(), field.name(), field.type());
			if (fv != null) {
				field.accept(fv);
			}
		}
		for (MethodModel method : methods) {
			MethodVisitor mv = visitor.visitMethod(method.accessFlags(), method.name(), method.type());
			if (mv != null) {
				method.accept(mv);
			}
		}
		visitor.visitSignature(signature);
		ModelHelper.accept(visitor, this);
	}
}
