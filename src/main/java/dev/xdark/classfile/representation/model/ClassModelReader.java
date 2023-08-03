package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.ClassVisitor;
import dev.xdark.classfile.representation.model.impl.ClassModelReaderImpl;

public interface ClassModelReader extends ClassVisitor {

	ClassModel visitEnd();

	static ClassModelReader create() {
		return new ClassModelReaderImpl();
	}
}
