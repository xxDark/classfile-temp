package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.CodeElement;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BinaryCondition;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.MathOperation;
import dev.xdark.classfile.representation.bytecode.UnaryCondition;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.instruction.AllocateInstruction;
import dev.xdark.classfile.representation.instruction.ArrayLengthInstruction;
import dev.xdark.classfile.representation.instruction.ArrayLoadInstruction;
import dev.xdark.classfile.representation.instruction.ArrayStoreInstruction;
import dev.xdark.classfile.representation.instruction.BinaryBranchInstruction;
import dev.xdark.classfile.representation.instruction.CheckCastInstruction;
import dev.xdark.classfile.representation.instruction.CompareInstruction;
import dev.xdark.classfile.representation.instruction.ConstantDoubleInstruction;
import dev.xdark.classfile.representation.instruction.ConstantDynamicInstruction;
import dev.xdark.classfile.representation.instruction.ConstantFloatInstruction;
import dev.xdark.classfile.representation.instruction.ConstantIntInstruction;
import dev.xdark.classfile.representation.instruction.ConstantMethodHandleInstruction;
import dev.xdark.classfile.representation.instruction.ConstantStringInstruction;
import dev.xdark.classfile.representation.instruction.ConstantTypeInstruction;
import dev.xdark.classfile.representation.instruction.FieldInstruction;
import dev.xdark.classfile.representation.instruction.GotoInstruction;
import dev.xdark.classfile.representation.instruction.IncrementInstruction;
import dev.xdark.classfile.representation.instruction.InstanceofInstruction;
import dev.xdark.classfile.representation.instruction.InvokeDynamicInstruction;
import dev.xdark.classfile.representation.instruction.InvokeInstruction;
import dev.xdark.classfile.representation.instruction.JumpSubroutineInstruction;
import dev.xdark.classfile.representation.instruction.LoadInstruction;
import dev.xdark.classfile.representation.instruction.LookupSwitchInstruction;
import dev.xdark.classfile.representation.instruction.MathInstruction;
import dev.xdark.classfile.representation.instruction.MonitorInstruction;
import dev.xdark.classfile.representation.instruction.NopInstruction;
import dev.xdark.classfile.representation.instruction.PrimitiveConversionInstruction;
import dev.xdark.classfile.representation.instruction.PushNullInstruction;
import dev.xdark.classfile.representation.instruction.ReturnFromSubroutineInstruction;
import dev.xdark.classfile.representation.instruction.ReturnInstruction;
import dev.xdark.classfile.representation.instruction.StackInstruction;
import dev.xdark.classfile.representation.instruction.StoreInstruction;
import dev.xdark.classfile.representation.instruction.TableSwitchInstruction;
import dev.xdark.classfile.representation.instruction.ThrowInstruction;
import dev.xdark.classfile.representation.instruction.UnaryBranchInstruction;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.PrimitiveType;

import java.util.List;

public abstract class CodeElementReader implements BytecodeVisitor {

	@Override
	public void nop() {
		accept(NopInstruction.create());
	}

	@Override
	public void pushNull() {
		accept(PushNullInstruction.create());
	}

	@Override
	public void pushInt(int value) {
		accept(ConstantIntInstruction.create(value));
	}

	@Override
	public void pushLong(long value) {
		accept(ConstantDoubleInstruction.create(value));
	}

	@Override
	public void pushFloat(float value) {
		accept(ConstantFloatInstruction.create(value));
	}

	@Override
	public void pushDouble(double value) {
		accept(ConstantDoubleInstruction.create(value));
	}

	@Override
	public void pushString(String value) {
		accept(ConstantStringInstruction.create(value));
	}

	@Override
	public void pushMethodHandle(MethodHandle methodHandle) {
		accept(ConstantMethodHandleInstruction.create(methodHandle));
	}

	@Override
	public void pushDynamic(ConstantDynamic dynamic) {
		accept(ConstantDynamicInstruction.create(dynamic));
	}

	@Override
	public void pushType(ObjectType type) {
		accept(ConstantTypeInstruction.create(type));
	}

	@Override
	public void pushType(MethodType type) {
		accept(ConstantTypeInstruction.create(type));
	}

	@Override
	public void load(ClassType type, int index) {
		accept(LoadInstruction.create(index, type));
	}

	@Override
	public void store(ClassType type, int index) {
		accept(StoreInstruction.create(index, type));
	}

	@Override
	public void arrayLoad(ClassType componentType) {
		accept(ArrayLoadInstruction.create(componentType));
	}

	@Override
	public void arrayStore(ClassType componentType) {
		accept(ArrayStoreInstruction.create(componentType));
	}

	@Override
	public void pop() {
		accept(StackInstruction.pop());
	}

	@Override
	public void pop2() {
		accept(StackInstruction.pop2());
	}

	@Override
	public void dup() {
		accept(StackInstruction.dup());
	}

	@Override
	public void dup_x1() {
		accept(StackInstruction.dup_x1());
	}

	@Override
	public void dup_x2() {
		accept(StackInstruction.dup_x2());
	}

	@Override
	public void dup2() {
		accept(StackInstruction.dup2());
	}

	@Override
	public void dup2_x1() {
		accept(StackInstruction.dup2_x1());
	}

	@Override
	public void dup2_x2() {
		accept(StackInstruction.dup2_x2());
	}

	@Override
	public void swap() {
		accept(StackInstruction.swap());
	}

	@Override
	public void mathOp(PrimitiveType type, MathOperation op) {
		accept(MathInstruction.create(type, op));
	}

	@Override
	public void increment(int variableIndex, int value) {
		accept(IncrementInstruction.create(variableIndex, value));
	}

	@Override
	public void primitiveConversion(PrimitiveType from, PrimitiveType to) {
		accept(PrimitiveConversionInstruction.create(from, to));
	}

	@Override
	public void lcmp() {
		accept(CompareInstruction.lcmp());
	}

	@Override
	public void fcmpl() {
		accept(CompareInstruction.fcmpl());
	}

	@Override
	public void fcmpg() {
		accept(CompareInstruction.fcmpg());
	}

	@Override
	public void dcmpl() {
		accept(CompareInstruction.dcmpl());
	}

	@Override
	public void dcmpg() {
		accept(CompareInstruction.dcmpg());
	}

	@Override
	public void label(Label label) {
		accept(label);
	}

	@Override
	public void unaryBranch(UnaryCondition condition, Label label) {
		accept(UnaryBranchInstruction.create(condition, label));
	}

	@Override
	public void binaryBranch(BinaryCondition condition, Label label) {
		accept(BinaryBranchInstruction.create(condition, label));
	}

	@Override
	public void goto_(Label label) {
		accept(GotoInstruction.create(label));
	}

	@Override
	public void jsr(Label label) {
		accept(JumpSubroutineInstruction.create(label));
	}

	@Override
	public void ret(int index) {
		accept(ReturnFromSubroutineInstruction.create(index));
	}

	@Override
	public void tableSwitch(Label defaultBranch, int low, int high, List<Label> cases) {
		accept(TableSwitchInstruction.create(defaultBranch, low, high, cases));
	}

	@Override
	public void lookupSwitch(Label defaultBranch, int[] keys, List<Label> cases) {
		accept(LookupSwitchInstruction.create(defaultBranch, keys, cases));
	}

	@Override
	public void return_(ClassType type) {
		accept(ReturnInstruction.create(type));
	}

	@Override
	public void getStatic(InstanceType owner, String name, ClassType type) {
		accept(FieldInstruction.getStatic(owner, name, type));
	}

	@Override
	public void putStatic(InstanceType owner, String name, ClassType type) {
		accept(FieldInstruction.putStatic(owner, name, type));
	}

	@Override
	public void getField(InstanceType owner, String name, ClassType type) {
		accept(FieldInstruction.getField(owner, name, type));
	}

	@Override
	public void putField(InstanceType owner, String name, ClassType type) {
		accept(FieldInstruction.putField(owner, name, type));
	}

	@Override
	public void invokeVirtual(ObjectType owner, String name, MethodType type) {
		accept(InvokeInstruction.invokeVirtual(owner, name, type));
	}

	@Override
	public void invokeSpecial(ObjectType owner, String name, MethodType type, boolean itf) {
		accept(InvokeInstruction.invokeSpecial(owner, name, type, itf));
	}

	@Override
	public void invokeStatic(ObjectType owner, String name, MethodType type, boolean itf) {
		accept(InvokeInstruction.invokeStatic(owner, name, type, itf));
	}

	@Override
	public void invokeInterface(ObjectType owner, String name, MethodType type) {
		accept(InvokeInstruction.invokeInterface(owner, name, type));
	}

	@Override
	public void invokeDynamic(InvokeDynamic invokeDynamic) {
		accept(InvokeDynamicInstruction.create(invokeDynamic));
	}

	@Override
	public void allocate(ObjectType type) {
		accept(AllocateInstruction.create(type));
	}

	@Override
	public void arrayLength() {
		accept(ArrayLengthInstruction.create());
	}

	@Override
	public void athrow() {
		accept(ThrowInstruction.create());
	}

	@Override
	public void checkCast(ObjectType type) {
		accept(CheckCastInstruction.create(type));
	}

	@Override
	public void instanceOf(ObjectType type) {
		accept(InstanceofInstruction.create(type));
	}

	@Override
	public void monitorEnter() {
		accept(MonitorInstruction.enter());
	}

	@Override
	public void monitorExit() {
		accept(MonitorInstruction.exit());
	}

	protected abstract void accept(CodeElement element);
}
