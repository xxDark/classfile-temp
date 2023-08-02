package dev.xdark.classfile.representation;

import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.impl.MutableSymbolTableImpl;
import dev.xdark.classfile.type.Type;

import java.util.ArrayList;

public interface MutableSymbolTable extends SymbolTable {

	@Override
	MutableConstantPool constantPool();

	int addString(String value);

	int addType(Type type);

	int addMethodHandle(MethodHandle methodHandle);

	int addInvokeDynamic(InvokeDynamic invokeDynamic);

	int addConstantDynamic(ConstantDynamic constantDynamic);

	BootstrapMethodsAttribute createBootstrapMethods();

	static MutableSymbolTable create(MutableConstantPool constantPool, BootstrapMethodsAttribute attribute) {
		return new MutableSymbolTableImpl(constantPool, attribute);
	}

	static MutableSymbolTable create(MutableConstantPool constantPool) {
		return create(constantPool, BootstrapMethodsAttribute.create(new ArrayList<>()));
	}
}
