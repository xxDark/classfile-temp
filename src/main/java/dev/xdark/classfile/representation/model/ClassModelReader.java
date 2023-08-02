package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.ClassVisitor;

public interface ClassModelReader extends ClassVisitor {

	ClassModel visitEnd();
}
