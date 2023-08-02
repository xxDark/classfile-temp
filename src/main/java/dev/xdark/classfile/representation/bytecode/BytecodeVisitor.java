package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.PrimitiveType;

import java.util.List;

public interface BytecodeVisitor {

	void nop();

	void pushNull();

	void pushInt(int value);

	void pushLong(long value);

	void pushFloat(float value);

	void pushDouble(double value);

	void pushString(String value);

	void pushMethodHandle(MethodHandle methodHandle);

	void pushDynamic(ConstantDynamic dynamic);

	void pushType(ObjectType type);

	void pushType(MethodType type);

	void load(ClassType type, int index);

	void store(ClassType type, int index);

	void arrayLoad(ClassType componentType);

	void arrayStore(ClassType componentType);

	void pop();

	void pop2();

	void dup();

	void dup_x1();

	void dup_x2();

	void dup2();

	void dup2_x1();

	void dup2_x2();

	void swap();

	void mathOp(PrimitiveType type, MathOperation op);

	void increment(int variableIndex, int value);

	void primitiveConversion(PrimitiveType from, PrimitiveType to);

	void lcmp();

	void fcmpl();

	void fcmpg();

	void dcmpl();

	void dcmpg();

	void label(Label label);

	void unaryBranch(UnaryCondition condition, Label label);

	void binaryBranch(BinaryCondition condition, Label label);

	void goto_(Label label);

	void jsr(Label label);

	void ret(int index);

	void tableSwitch(Label defaultBranch, int low, int high, List<Label> cases);

	void lookupSwitch(Label defaultBranch, int[] keys, List<Label> cases);

	void return_(ClassType type);

	void getStatic(InstanceType owner, String name, ClassType type);

	void putStatic(InstanceType owner, String name, ClassType type);

	void getField(InstanceType owner, String name, ClassType type);

	void putField(InstanceType owner, String name, ClassType type);

	void invokeVirtual(ObjectType owner, String name, MethodType type);

	void invokeSpecial(ObjectType owner, String name, MethodType type, boolean itf);

	void invokeStatic(ObjectType owner, String name, MethodType type, boolean itf);

	void invokeInterface(ObjectType owner, String name, MethodType type);

	void invokeDynamic(InvokeDynamic invokeDynamic);

	void allocate(ObjectType type);

	void arrayLength();

	void athrow();

	void checkCast(ObjectType type);

	void instanceOf(ObjectType type);

	void monitorEnter();

	void monitorExit();
}
