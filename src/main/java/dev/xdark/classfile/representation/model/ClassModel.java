package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.representation.Accessible;
import dev.xdark.classfile.representation.Attributed;
import dev.xdark.classfile.representation.Signed;
import dev.xdark.classfile.representation.Typed;
import dev.xdark.classfile.type.InstanceType;

import java.util.List;

public interface ClassModel extends Accessible, Typed, Signed, Attributed {

	ClassFileVersion version();

	@Override
	InstanceType type();

	InstanceType superType();

	List<InstanceType> interfaces();

	List<MethodModel> methods();

	List<FieldModel> fields();

	void accept(ClassModelDumper dumper);
}
