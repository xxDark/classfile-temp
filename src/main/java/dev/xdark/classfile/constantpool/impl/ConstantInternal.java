package dev.xdark.classfile.constantpool.impl;

import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

/**
 * Internal translation layer for faster speed.
 */
public interface ConstantInternal {

	LoadableConstant asLoadable(SymbolTable symtab);
}
