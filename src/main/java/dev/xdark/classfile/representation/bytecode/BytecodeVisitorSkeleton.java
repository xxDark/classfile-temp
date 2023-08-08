package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.stacmap.Frame;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.PrimitiveType;

import java.util.List;

public class BytecodeVisitorSkeleton implements BytecodeVisitor {

	@Override
	public void nop() {

	}

	@Override
	public void pushNull() {

	}

	@Override
	public void pushInt(int value) {

	}

	@Override
	public void pushLong(long value) {

	}

	@Override
	public void pushFloat(float value) {

	}

	@Override
	public void pushDouble(double value) {

	}

	@Override
	public void pushString(String value) {

	}

	@Override
	public void pushMethodHandle(MethodHandle methodHandle) {

	}

	@Override
	public void pushDynamic(ConstantDynamic dynamic) {

	}

	@Override
	public void pushType(ObjectType type) {

	}

	@Override
	public void pushType(MethodType type) {

	}

	@Override
	public void load(ClassType type, int index) {

	}

	@Override
	public void store(ClassType type, int index) {

	}

	@Override
	public void arrayLoad(ClassType componentType) {

	}

	@Override
	public void arrayStore(ClassType componentType) {

	}

	@Override
	public void pop() {

	}

	@Override
	public void pop2() {

	}

	@Override
	public void dup() {

	}

	@Override
	public void dup_x1() {

	}

	@Override
	public void dup_x2() {

	}

	@Override
	public void dup2() {

	}

	@Override
	public void dup2_x1() {

	}

	@Override
	public void dup2_x2() {

	}

	@Override
	public void swap() {

	}

	@Override
	public void mathOp(PrimitiveType type, MathOperation op) {

	}

	@Override
	public void increment(int variableIndex, int value) {

	}

	@Override
	public void primitiveConversion(PrimitiveType from, PrimitiveType to) {

	}

	@Override
	public void lcmp() {

	}

	@Override
	public void fcmpl() {

	}

	@Override
	public void fcmpg() {

	}

	@Override
	public void dcmpl() {

	}

	@Override
	public void dcmpg() {

	}

	@Override
	public void label(Label label) {

	}

	@Override
	public void unaryBranch(UnaryCondition condition, Label label) {

	}

	@Override
	public void binaryBranch(BinaryCondition condition, Label label) {

	}

	@Override
	public void goto_(Label label) {

	}

	@Override
	public void jsr(Label label) {

	}

	@Override
	public void ret(int index) {

	}

	@Override
	public void tableSwitch(Label defaultBranch, int low, int high, List<Label> cases) {

	}

	@Override
	public void lookupSwitch(Label defaultBranch, int[] keys, List<Label> cases) {

	}

	@Override
	public void return_(ClassType type) {

	}

	@Override
	public void getStatic(InstanceType owner, String name, ClassType type) {

	}

	@Override
	public void putStatic(InstanceType owner, String name, ClassType type) {

	}

	@Override
	public void getField(InstanceType owner, String name, ClassType type) {

	}

	@Override
	public void putField(InstanceType owner, String name, ClassType type) {

	}

	@Override
	public void invokeVirtual(ObjectType owner, String name, MethodType type) {

	}

	@Override
	public void invokeSpecial(ObjectType owner, String name, MethodType type, boolean itf) {

	}

	@Override
	public void invokeStatic(ObjectType owner, String name, MethodType type, boolean itf) {

	}

	@Override
	public void invokeInterface(ObjectType owner, String name, MethodType type) {

	}

	@Override
	public void invokeDynamic(InvokeDynamic invokeDynamic) {

	}

	@Override
	public void allocate(ObjectType type) {

	}

	@Override
	public void arrayLength() {

	}

	@Override
	public void athrow() {

	}

	@Override
	public void checkCast(ObjectType type) {

	}

	@Override
	public void instanceOf(ObjectType type) {

	}

	@Override
	public void monitorEnter() {

	}

	@Override
	public void monitorExit() {

	}
}
