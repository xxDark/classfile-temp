package dev.xdark.classfile.representation.model;

import dev.xdark.classfile.representation.Attributed;
import dev.xdark.classfile.representation.CodeElement;
import dev.xdark.classfile.representation.TryCatchBlock;

import java.util.List;

public interface CodeModel extends Attributed {

	int maxStack();

	int maxLocals();

	List<CodeElement> elements();

	List<TryCatchBlock> tryCatchBlocks();
}
