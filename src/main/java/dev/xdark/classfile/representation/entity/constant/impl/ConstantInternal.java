package dev.xdark.classfile.representation.entity.constant.impl;

import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;

/**
 * Internal translation layer for faster speed.
 */
public interface ConstantInternal {

	int store(MutableSymbolTable symtab);

	ConstantInstruction<?> asInstruction();
}
