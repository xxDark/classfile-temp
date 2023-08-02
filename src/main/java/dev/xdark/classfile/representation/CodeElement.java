package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public interface CodeElement {

	void accept(BytecodeVisitor visitor);
}
