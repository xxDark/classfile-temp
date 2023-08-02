package dev.xdark.classfile.representation.entity.constant;

import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;

public interface ConstantSink {

	void acceptString(String value);

	void acceptLong(long value);

	void acceptDouble(double value);

	void acceptInt(int value);

	void acceptFloat(float value);

	void acceptType(ObjectType value);

	void acceptType(MethodType value);

	void acceptMethodHandle(MethodHandle methodHandle);

	void acceptDynamic(ConstantDynamic dynamic);
}
