package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.ClassVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;

public interface ClassModelDumper extends ClassVisitor {

	MutableSymbolTable visitSymbolTable();

	@Override
	default MutableConstantPool visitConstantPool() {
		return visitSymbolTable().constantPool();
	}
}
