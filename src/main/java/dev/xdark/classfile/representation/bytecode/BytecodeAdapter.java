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

public abstract class BytecodeAdapter implements BytecodeVisitor {
	protected final BytecodeVisitor bv;

	protected BytecodeAdapter(BytecodeVisitor bv) {
		this.bv = bv;
	}

	@Override
	public void nop() {
		bv.nop();
	}

	@Override
	public void pushNull() {
		bv.pushNull();
	}

	@Override
	public void pushInt(int value) {
		bv.pushInt(value);
	}

	@Override
	public void pushLong(long value) {
		bv.pushLong(value);
	}

	@Override
	public void pushFloat(float value) {
		bv.pushFloat(value);
	}

	@Override
	public void pushDouble(double value) {
		bv.pushDouble(value);
	}

	@Override
	public void pushString(String value) {
		bv.pushString(value);
	}

	@Override
	public void pushMethodHandle(MethodHandle methodHandle) {
		bv.pushMethodHandle(methodHandle);
	}

	@Override
	public void pushDynamic(ConstantDynamic dynamic) {
		bv.pushDynamic(dynamic);
	}

	@Override
	public void pushType(ObjectType type) {
		bv.pushType(type);
	}

	@Override
	public void pushType(MethodType type) {
		bv.pushType(type);
	}

	@Override
	public void load(ClassType type, int index) {
		bv.load(type, index);
	}

	@Override
	public void store(ClassType type, int index) {
		bv.store(type, index);
	}

	@Override
	public void arrayLoad(ClassType componentType) {
		bv.arrayLoad(componentType);
	}

	@Override
	public void arrayStore(ClassType componentType) {
		bv.arrayStore(componentType);
	}

	@Override
	public void pop() {
		bv.pop();
	}

	@Override
	public void pop2() {
		bv.pop2();
	}

	@Override
	public void dup() {
		bv.dup();
	}

	@Override
	public void dup_x1() {
		bv.dup_x1();
	}

	@Override
	public void dup_x2() {
		bv.dup_x2();
	}

	@Override
	public void dup2() {
		bv.dup2();
	}

	@Override
	public void dup2_x1() {
		bv.dup2_x1();
	}

	@Override
	public void dup2_x2() {
		bv.dup2_x2();
	}

	@Override
	public void swap() {
		bv.swap();
	}

	@Override
	public void mathOp(PrimitiveType type, MathOperation op) {
		bv.mathOp(type, op);
	}

	@Override
	public void increment(int variableIndex, int value) {
		bv.increment(variableIndex, value);
	}

	@Override
	public void primitiveConversion(PrimitiveType from, PrimitiveType to) {
		bv.primitiveConversion(from, to);
	}

	@Override
	public void lcmp() {
		bv.lcmp();
	}

	@Override
	public void fcmpl() {
		bv.fcmpl();
	}

	@Override
	public void fcmpg() {
		bv.fcmpg();
	}

	@Override
	public void dcmpl() {
		bv.dcmpl();
	}

	@Override
	public void dcmpg() {
		bv.dcmpg();
	}

	@Override
	public void label(Label label) {
		bv.label(label);
	}

	@Override
	public void unaryBranch(UnaryCondition condition, Label label) {
		bv.unaryBranch(condition, label);
	}

	@Override
	public void binaryBranch(BinaryCondition condition, Label label) {
		bv.binaryBranch(condition, label);
	}

	@Override
	public void goto_(Label label) {
		bv.goto_(label);
	}

	@Override
	public void jsr(Label label) {
		bv.jsr(label);
	}

	@Override
	public void ret(int index) {
		bv.ret(index);
	}

	@Override
	public void tableSwitch(Label defaultBranch, int low, int high, List<Label> cases) {
		bv.tableSwitch(defaultBranch, low, high, cases);
	}

	@Override
	public void lookupSwitch(Label defaultBranch, int[] keys, List<Label> cases) {
		bv.lookupSwitch(defaultBranch, keys, cases);
	}

	@Override
	public void return_(ClassType type) {
		bv.return_(type);
	}

	@Override
	public void getStatic(InstanceType owner, String name, ClassType type) {
		bv.getStatic(owner, name, type);
	}

	@Override
	public void putStatic(InstanceType owner, String name, ClassType type) {
		bv.putStatic(owner, name, type);
	}

	@Override
	public void getField(InstanceType owner, String name, ClassType type) {
		bv.getField(owner, name, type);
	}

	@Override
	public void putField(InstanceType owner, String name, ClassType type) {
		bv.putField(owner, name, type);
	}

	@Override
	public void invokeVirtual(ObjectType owner, String name, MethodType type) {
		bv.invokeVirtual(owner, name, type);
	}

	@Override
	public void invokeSpecial(ObjectType owner, String name, MethodType type, boolean itf) {
		bv.invokeSpecial(owner, name, type, itf);
	}

	@Override
	public void invokeStatic(ObjectType owner, String name, MethodType type, boolean itf) {
		bv.invokeStatic(owner, name, type, itf);
	}

	@Override
	public void invokeInterface(ObjectType owner, String name, MethodType type) {
		bv.invokeInterface(owner, name, type);
	}

	@Override
	public void invokeDynamic(InvokeDynamic invokeDynamic) {
		bv.invokeDynamic(invokeDynamic);
	}

	@Override
	public void allocate(ObjectType type) {
		bv.allocate(type);
	}

	@Override
	public void arrayLength() {
		bv.arrayLength();
	}

	@Override
	public void athrow() {
		bv.athrow();
	}

	@Override
	public void checkCast(ObjectType type) {
		bv.checkCast(type);
	}

	@Override
	public void instanceOf(ObjectType type) {
		bv.instanceOf(type);
	}

	@Override
	public void monitorEnter() {
		bv.monitorEnter();
	}

	@Override
	public void monitorExit() {
		bv.monitorExit();
	}
}
