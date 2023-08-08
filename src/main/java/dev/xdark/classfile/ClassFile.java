package dev.xdark.classfile;

import dev.xdark.classfile.attribute.AttributeMapper;
import dev.xdark.classfile.attribute.DefaultAttributeMapper;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.impl.ClassFileImpl;
import dev.xdark.classfile.io.BinaryInput;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public interface ClassFile {
	int MAGIC = 0xcafebabe;

	void read(BinaryInput input, ClassVisitor visitor);

	ClassAssembler newAssembler(ConstantPool constantPool);

	ClassAssembler newAssembler();

	static ClassFile of(AttributeMapper attributeMapper, Set<? extends Option> options) {
		return new ClassFileImpl(attributeMapper, options);
	}

	static ClassFile of(AttributeMapper attributeMapper) {
		return of(attributeMapper, Collections.emptySet());
	}

	static ClassFile of(Set<? extends Option> options) {
		return of(new DefaultAttributeMapper(), options);
	}

	static ClassFile of() {
		return of(Collections.emptySet());
	}
}
