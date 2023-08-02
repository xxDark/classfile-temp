package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

public interface CodeVisitor extends Attributable {

	void visitMaxs(int maxStack, int maxLocals);

	void visitTryCatchBlock(TryCatchBlock tryCatchBlock);

	BytecodeVisitor visitBytecode();
}
