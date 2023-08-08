package dev.xdark.classfile;

import dev.xdark.classfile.io.BinaryOutput;

import java.io.IOException;

public interface ClassAssembler extends ClassVisitor {

	void dump(BinaryOutput output);
}
