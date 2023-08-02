package dev.xdark.classfile;

import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BinaryCondition;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.MathOperation;
import dev.xdark.classfile.representation.bytecode.UnaryCondition;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.PrimitiveType;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

final class BytecodeTracer implements BytecodeVisitor {
	@Override
	public void nop() {
		System.out.println("nop");
	}

	@Override
	public void pushNull() {
		System.out.println("aconst_null");
	}

	@Override
	public void pushInt(int value) {
		System.out.printf("ldc %d%n", value);
	}

	@Override
	public void pushLong(long value) {
		System.out.printf("ldc %d%n", value);
	}

	@Override
	public void pushFloat(float value) {
		System.out.printf("ldc %f%n", value);
	}

	@Override
	public void pushDouble(double value) {
		System.out.printf("ldc %f%n", value);
	}

	@Override
	public void pushString(String value) {
		System.out.printf("ldc \"%s\"%n", value);
	}

	@Override
	public void pushMethodHandle(MethodHandle methodHandle) {
		System.out.printf("ldc %s%n", formatMethodHandle(methodHandle));
	}

	@Override
	public void pushDynamic(ConstantDynamic dynamic) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void pushType(ObjectType type) {
		System.out.printf("class %s%n", type.internalName());
	}

	@Override
	public void pushType(MethodType type) {
		System.out.printf("method_type %s%n", type.descriptor());
	}

	@Override
	public void load(ClassType type, int index) {
		System.out.printf("%sload %d%n", classPrefix(type), index);
	}

	@Override
	public void store(ClassType type, int index) {
		System.out.printf("%sstore %d%n", classPrefix(type), index);
	}

	@Override
	public void arrayLoad(ClassType componentType) {
		System.out.printf("%saload%n", classPrefix(componentType));
	}

	@Override
	public void arrayStore(ClassType componentType) {
		System.out.printf("%sastore%n", classPrefix(componentType));
	}

	@Override
	public void pop() {
		System.out.println("pop");
	}

	@Override
	public void pop2() {
		System.out.println("pop2");
	}

	@Override
	public void dup() {
		System.out.println("dup");
	}

	@Override
	public void dup_x1() {
		System.out.println("dup_x1");
	}

	@Override
	public void dup_x2() {
		System.out.println("dup_x2");
	}

	@Override
	public void dup2() {
		System.out.println("dup2");
	}

	@Override
	public void dup2_x1() {
		System.out.println("dup2_x1");
	}

	@Override
	public void dup2_x2() {
		System.out.println("dup2_x2");
	}

	@Override
	public void swap() {
		System.out.println("swap");
	}

	@Override
	public void mathOp(PrimitiveType type, MathOperation op) {
		System.out.printf("%s%s%n", type.name().charAt(0), op.name().toLowerCase(Locale.ENGLISH));
	}

	@Override
	public void increment(int variableIndex, int value) {
		System.out.printf("innc %d %d%n", variableIndex, value);
	}

	@Override
	public void primitiveConversion(PrimitiveType from, PrimitiveType to) {
		System.out.printf("%s2%s%n", from.name().charAt(0), to.name().charAt(0));
	}

	@Override
	public void lcmp() {
		System.out.println("lcmp");
	}

	@Override
	public void fcmpl() {
		System.out.println("fcmpl");
	}

	@Override
	public void fcmpg() {
		System.out.println("fcmpg");
	}

	@Override
	public void dcmpl() {
		System.out.println("dcmpl");
	}

	@Override
	public void dcmpg() {
		System.out.println("dcmpg");
	}

	@Override
	public void label(Label label) {
		System.out.printf("%s:%n", formatLabel(label));
	}

	@Override
	public void unaryBranch(UnaryCondition condition, Label label) {
		System.out.printf("if_%s %s%n", condition.name().toLowerCase(Locale.ENGLISH), formatLabel(label));
	}

	@Override
	public void binaryBranch(BinaryCondition condition, Label label) {
		String name;
		if (condition == BinaryCondition.AEQ) {
			name = "acmpeq";
		} else if (condition == BinaryCondition.ANE) {
			name = "acmpne";
		} else {
			name = String.format("icmp%s", condition.name().toLowerCase(Locale.ENGLISH));
		}
		System.out.printf("if_%s %s%n", name, formatLabel(label));
	}

	@Override
	public void goto_(Label label) {
		System.out.printf("goto %s%n", formatLabel(label));
	}

	@Override
	public void jsr(Label label) {
		System.out.printf("jsr %s%n", formatLabel(label));
	}

	@Override
	public void ret(int index) {
		System.out.printf("ret %d%n", index);
	}

	@Override
	public void tableSwitch(Label defaultBranch, int low, int high, List<Label> cases) {
		System.out.printf("table_switch %s..%s, %s, default: %s", low, high, formatLabels(cases), formatLabel(defaultBranch));
	}

	@Override
	public void lookupSwitch(Label defaultBranch, int[] keys, List<Label> cases) {
		System.out.printf("lookup_switch %s, %s, default: %s", Arrays.toString(keys), formatLabels(cases), formatLabel(defaultBranch));
	}

	@Override
	public void return_(ClassType type) {
		String prefix;
		if (type == PrimitiveType.T_VOID) {
			prefix = "";
		} else {
			prefix = classPrefix(type);
		}
		System.out.printf("%sreturn%n", prefix);
	}

	@Override
	public void getStatic(InstanceType owner, String name, ClassType type) {
		System.out.printf("getstatic %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void putStatic(InstanceType owner, String name, ClassType type) {
		System.out.printf("putstatic %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void getField(InstanceType owner, String name, ClassType type) {
		System.out.printf("getfield %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void putField(InstanceType owner, String name, ClassType type) {
		System.out.printf("putfield %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void invokeVirtual(ObjectType owner, String name, MethodType type) {
		System.out.printf("invokevirtual %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void invokeSpecial(ObjectType owner, String name, MethodType type, boolean itf) {
		System.out.printf("invokespecial %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void invokeStatic(ObjectType owner, String name, MethodType type, boolean itf) {
		System.out.printf("invokestatic %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void invokeInterface(ObjectType owner, String name, MethodType type) {
		System.out.printf("invokeinterface %s.%s%s%n", owner.internalName(), name, type.descriptor());
	}

	@Override
	public void invokeDynamic(InvokeDynamic invokeDynamic) {
		MethodHandle mh = invokeDynamic.bootstrapMethodHandle();
		System.out.printf("invokedynamic %s%s Bootstrap(%s)%n", invokeDynamic.name(), invokeDynamic.type().descriptor(), formatMethodHandle(mh));
	}

	@Override
	public void allocate(ObjectType type) {
		System.out.printf("allocate %s%n", type.internalName());
	}

	@Override
	public void arrayLength() {
		System.out.println("array_length");
	}

	@Override
	public void athrow() {
		System.out.println("athrow");
	}

	@Override
	public void checkCast(ObjectType type) {
		System.out.printf("checkcast %s%n", type.internalName());
	}

	@Override
	public void instanceOf(ObjectType type) {
		System.out.printf("instanceof %s%n", type.internalName());
	}

	@Override
	public void monitorEnter() {
		System.out.println("monitor_enter");
	}

	@Override
	public void monitorExit() {
		System.out.println("monitor_exit");
	}

	private static String formatMethodHandle(MethodHandle methodHandle) {
		return String.format("%s %s%s.%s", methodHandle.kind(), methodHandle.owner().internalName(), methodHandle.name(), methodHandle.type().descriptor());
	}

	private static String formatLabels(List<Label> label) {
		return label.stream().map(BytecodeTracer::formatLabel).collect(Collectors.joining(", "));
	}

	static String formatLabel(Label label) {
		return String.format("label_%d", label.getPosition());
	}

	private static String classPrefix(ClassType type) {
		if (type instanceof ObjectType) {
			return "a";
		} else {
			return Character.toString(((PrimitiveType) type).name().charAt(0));
		}
	}
}
