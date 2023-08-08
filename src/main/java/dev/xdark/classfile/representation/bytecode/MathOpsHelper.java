package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.bytecode.BytecodeVisitor;
import dev.xdark.classfile.type.PrimitiveKind;

import java.util.function.Consumer;

public final class MathOpsHelper {
	private static final Consumer<BytecodeVisitor>[] MATH_OPS;

	private MathOpsHelper() {
	}

	public static void init(){}

	static {
		MATH_OPS = new Consumer[(PrimitiveKind.T_LONG.ordinal() | MathOperation.XOR.ordinal() << 4) + 1];
		setMathOp(MathOperation.ADD, PrimitiveKind.T_INT, BytecodeVisitor::iadd);
		setMathOp(MathOperation.ADD, PrimitiveKind.T_LONG, BytecodeVisitor::ladd);
		setMathOp(MathOperation.ADD, PrimitiveKind.T_DOUBLE, BytecodeVisitor::dadd);
		setMathOp(MathOperation.ADD, PrimitiveKind.T_FLOAT, BytecodeVisitor::fadd);
		setMathOp(MathOperation.SUB, PrimitiveKind.T_INT, BytecodeVisitor::isub);
		setMathOp(MathOperation.SUB, PrimitiveKind.T_LONG, BytecodeVisitor::lsub);
		setMathOp(MathOperation.SUB, PrimitiveKind.T_DOUBLE, BytecodeVisitor::dsub);
		setMathOp(MathOperation.SUB, PrimitiveKind.T_FLOAT, BytecodeVisitor::fsub);
		setMathOp(MathOperation.MUL, PrimitiveKind.T_INT, BytecodeVisitor::imul);
		setMathOp(MathOperation.MUL, PrimitiveKind.T_LONG, BytecodeVisitor::lmul);
		setMathOp(MathOperation.MUL, PrimitiveKind.T_DOUBLE, BytecodeVisitor::dmul);
		setMathOp(MathOperation.MUL, PrimitiveKind.T_FLOAT, BytecodeVisitor::fmul);
		setMathOp(MathOperation.DIV, PrimitiveKind.T_INT, BytecodeVisitor::idiv);
		setMathOp(MathOperation.DIV, PrimitiveKind.T_LONG, BytecodeVisitor::ldiv);
		setMathOp(MathOperation.DIV, PrimitiveKind.T_DOUBLE, BytecodeVisitor::ddiv);
		setMathOp(MathOperation.DIV, PrimitiveKind.T_FLOAT, BytecodeVisitor::fdiv);
		setMathOp(MathOperation.REM, PrimitiveKind.T_INT, BytecodeVisitor::irem);
		setMathOp(MathOperation.REM, PrimitiveKind.T_LONG, BytecodeVisitor::lrem);
		setMathOp(MathOperation.REM, PrimitiveKind.T_DOUBLE, BytecodeVisitor::drem);
		setMathOp(MathOperation.REM, PrimitiveKind.T_FLOAT, BytecodeVisitor::frem);
		setMathOp(MathOperation.NEG, PrimitiveKind.T_INT, BytecodeVisitor::ineg);
		setMathOp(MathOperation.NEG, PrimitiveKind.T_LONG, BytecodeVisitor::lneg);
		setMathOp(MathOperation.NEG, PrimitiveKind.T_DOUBLE, BytecodeVisitor::dneg);
		setMathOp(MathOperation.NEG, PrimitiveKind.T_FLOAT, BytecodeVisitor::fneg);

		setMathOp(MathOperation.SHL, PrimitiveKind.T_INT, BytecodeVisitor::ishl);
		setMathOp(MathOperation.SHL, PrimitiveKind.T_LONG, BytecodeVisitor::lshl);
		setMathOp(MathOperation.SHR, PrimitiveKind.T_INT, BytecodeVisitor::ishr);
		setMathOp(MathOperation.SHR, PrimitiveKind.T_LONG, BytecodeVisitor::ishr);
		setMathOp(MathOperation.USHR, PrimitiveKind.T_INT, BytecodeVisitor::iushr);
		setMathOp(MathOperation.USHR, PrimitiveKind.T_LONG, BytecodeVisitor::iushr);
		setMathOp(MathOperation.AND, PrimitiveKind.T_INT, BytecodeVisitor::iand);
		setMathOp(MathOperation.AND, PrimitiveKind.T_LONG, BytecodeVisitor::land);
		setMathOp(MathOperation.OR, PrimitiveKind.T_INT, BytecodeVisitor::ior);
		setMathOp(MathOperation.OR, PrimitiveKind.T_LONG, BytecodeVisitor::lor);
		setMathOp(MathOperation.XOR, PrimitiveKind.T_INT, BytecodeVisitor::ixor);
		setMathOp(MathOperation.XOR, PrimitiveKind.T_LONG, BytecodeVisitor::lxor);
	}

	static void doOp(BytecodeVisitor bv, MathOperation op, PrimitiveKind kind) {
		MATH_OPS[kind.ordinal() | op.ordinal() << 4].accept(bv);
	}

	private static void setMathOp(MathOperation op, PrimitiveKind kind, Consumer<BytecodeVisitor> c) {
		MATH_OPS[kind.ordinal() | op.ordinal() << 4] = c;
	}
}
