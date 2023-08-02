package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;

public final class BytecodeConstantSink implements ConstantSink {
	private final BytecodeVisitor bv;

	public BytecodeConstantSink(BytecodeVisitor bv) {
		this.bv = bv;
	}

	@Override
	public void acceptString(String value) {
		bv.pushString(value);
	}

	@Override
	public void acceptLong(long value) {
		bv.pushLong(value);
	}

	@Override
	public void acceptDouble(double value) {
		bv.pushDouble(value);
	}

	@Override
	public void acceptInt(int value) {
		bv.pushInt(value);
	}

	@Override
	public void acceptFloat(float value) {
		bv.pushFloat(value);
	}

	@Override
	public void acceptType(ObjectType value) {
		bv.pushType(value);
	}

	@Override
	public void acceptType(MethodType value) {
		bv.pushType(value);
	}

	@Override
	public void acceptMethodHandle(MethodHandle methodHandle) {
		bv.pushMethodHandle(methodHandle);
	}

	@Override
	public void acceptDynamic(ConstantDynamic dynamic) {
		bv.pushDynamic(dynamic);
	}
}
