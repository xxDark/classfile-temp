package dev.xdark.classfile.representation.impl;

import dev.xdark.classfile.constantpool.ConstantDynamic;
import dev.xdark.classfile.constantpool.impl.ConstantInternal;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;

public final class SymbolTableHelper {

	private SymbolTableHelper() {
	}

	public static LoadableConstant asLoadable(SymbolTable symtab, int idx) {
		dev.xdark.classfile.constantpool.Constant cst = symtab.constantPool().get(idx);
		if (cst instanceof ConstantDynamic) {
			return LoadableConstant.create(symtab.getConstantDynamic(idx));
		}
		return ((ConstantInternal) cst).asLoadable(symtab);
	}
}
