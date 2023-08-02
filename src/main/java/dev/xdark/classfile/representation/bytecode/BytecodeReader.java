package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.bytecode.BytecodeVisitor;
import dev.xdark.classfile.constantpool.ConstantFieldRef;
import dev.xdark.classfile.constantpool.ConstantInterfaceMethodRef;
import dev.xdark.classfile.constantpool.ConstantMemberRef;
import dev.xdark.classfile.constantpool.ConstantNameAndType;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.bytecode.BinaryCondition;
import dev.xdark.classfile.representation.bytecode.BytecodeConstantSink;
import dev.xdark.classfile.representation.bytecode.LabelArray;
import dev.xdark.classfile.representation.bytecode.LabelPlacementTracker;
import dev.xdark.classfile.representation.bytecode.MathOperation;
import dev.xdark.classfile.representation.bytecode.UnaryCondition;
import dev.xdark.classfile.representation.entity.constant.ConstantSink;
import dev.xdark.classfile.type.ArrayType;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.PrimitiveKind;
import dev.xdark.classfile.type.PrimitiveType;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static dev.xdark.classfile.bytecode.Bytecodes.*;

public final class BytecodeReader implements BytecodeVisitor {
	private static final InstanceType OBJ = InstanceType.ofClass(Object.class);
	private final SymbolTable symbolTable;
	private final dev.xdark.classfile.representation.bytecode.BytecodeVisitor bv;
	private final ConstantSink constantSink;
	private final LabelArray labels;
	private final LabelPlacementTracker tracker;

	public BytecodeReader(SymbolTable symbolTable, dev.xdark.classfile.representation.bytecode.BytecodeVisitor bv, LabelArray labels, LabelPlacementTracker placementTracker) {
		this.symbolTable = symbolTable;
		this.bv = bv;
		this.labels = labels;
		constantSink = new BytecodeConstantSink(bv);
		tracker = placementTracker;
	}

	@Override
	public void nop() {
		bv.nop();
	}

	@Override
	public void aconst_null() {
		bv.pushNull();
	}

	@Override
	public void iconst(int n) {
		bv.pushInt(n);
	}

	@Override
	public void lconst_0() {
		bv.pushLong(0L);
	}

	@Override
	public void lconst_1() {
		bv.pushLong(1L);
	}

	@Override
	public void fconst_0() {
		bv.pushFloat(0.0F);
	}

	@Override
	public void fconst_1() {
		bv.pushFloat(1.0F);
	}

	@Override
	public void fconst_2() {
		bv.pushFloat(2.0F);
	}

	@Override
	public void dconst_0() {
		bv.pushDouble(0.0D);
	}

	@Override
	public void dconst_1() {
		bv.pushDouble(1.0D);
	}

	@Override
	public void bipush(int value) {
		bv.pushInt(value);
	}

	@Override
	public void sipush(int value) {
		bv.pushInt(value);
	}

	@Override
	public void ldc(int index) {
		symbolTable.getConstant(index).accept(constantSink);
	}

	@Override
	public void ldc_w(int index) {
		symbolTable.getConstant(index).accept(constantSink);
	}

	@Override
	public void ldc2_w(int index) {
		symbolTable.getConstant(index).accept(constantSink);
	}

	@Override
	public void iload(int index) {
		bv.load(PrimitiveType.T_INT, index);
	}

	@Override
	public void lload(int index) {
		bv.load(PrimitiveType.T_LONG, index);
	}

	@Override
	public void fload(int index) {
		bv.load(PrimitiveType.T_FLOAT, index);
	}

	@Override
	public void dload(int index) {
		bv.load(PrimitiveType.T_DOUBLE, index);
	}

	@Override
	public void aload(int index) {
		bv.load(OBJ, index);
	}

	@Override
	public void iload_0() {
		bv.load(PrimitiveType.T_INT, 0);
	}

	@Override
	public void iload_1() {
		bv.load(PrimitiveType.T_INT, 1);
	}

	@Override
	public void iload_2() {
		bv.load(PrimitiveType.T_INT, 2);
	}

	@Override
	public void iload_3() {
		bv.load(PrimitiveType.T_INT, 3);
	}

	@Override
	public void lload_0() {
		bv.load(PrimitiveType.T_LONG, 0);
	}

	@Override
	public void lload_1() {
		bv.load(PrimitiveType.T_LONG, 1);
	}

	@Override
	public void lload_2() {
		bv.load(PrimitiveType.T_LONG, 2);
	}

	@Override
	public void lload_3() {
		bv.load(PrimitiveType.T_LONG, 3);
	}

	@Override
	public void fload_0() {
		bv.load(PrimitiveType.T_FLOAT, 0);
	}

	@Override
	public void fload_1() {
		bv.load(PrimitiveType.T_FLOAT, 1);
	}

	@Override
	public void fload_2() {
		bv.load(PrimitiveType.T_FLOAT, 2);
	}

	@Override
	public void fload_3() {
		bv.load(PrimitiveType.T_FLOAT, 3);
	}

	@Override
	public void dload_0() {
		bv.load(PrimitiveType.T_DOUBLE, 0);
	}

	@Override
	public void dload_1() {
		bv.load(PrimitiveType.T_DOUBLE, 1);
	}

	@Override
	public void dload_2() {
		bv.load(PrimitiveType.T_DOUBLE, 2);
	}

	@Override
	public void dload_3() {
		bv.load(PrimitiveType.T_DOUBLE, 3);
	}

	@Override
	public void aload_0() {
		bv.load(OBJ, 0);
	}

	@Override
	public void aload_1() {
		bv.load(OBJ, 1);
	}

	@Override
	public void aload_2() {
		bv.load(OBJ, 2);
	}

	@Override
	public void aload_3() {
		bv.load(OBJ, 3);
	}

	@Override
	public void iaload() {
		bv.arrayLoad(PrimitiveType.T_INT);
	}

	@Override
	public void laload() {
		bv.arrayLoad(PrimitiveType.T_LONG);
	}

	@Override
	public void faload() {
		bv.arrayLoad(PrimitiveType.T_FLOAT);
	}

	@Override
	public void daload() {
		bv.arrayLoad(PrimitiveType.T_DOUBLE);
	}

	@Override
	public void aaload() {
		bv.arrayLoad(OBJ);
	}

	@Override
	public void baload() {
		bv.arrayLoad(PrimitiveType.T_BYTE);
	}

	@Override
	public void caload() {
		bv.arrayLoad(PrimitiveType.T_CHAR);
	}

	@Override
	public void saload() {
		bv.arrayLoad(PrimitiveType.T_SHORT);
	}

	@Override
	public void istore(int index) {
		bv.store(PrimitiveType.T_INT, index);
	}

	@Override
	public void lstore(int index) {
		bv.store(PrimitiveType.T_LONG, index);
	}

	@Override
	public void fstore(int index) {
		bv.store(PrimitiveType.T_FLOAT, index);
	}

	@Override
	public void dstore(int index) {
		bv.store(PrimitiveType.T_DOUBLE, index);
	}

	@Override
	public void astore(int index) {
		bv.store(OBJ, index);
	}

	@Override
	public void istore_0() {
		bv.store(PrimitiveType.T_INT, 0);
	}

	@Override
	public void istore_1() {
		bv.store(PrimitiveType.T_INT, 1);
	}

	@Override
	public void istore_2() {
		bv.store(PrimitiveType.T_INT, 2);
	}

	@Override
	public void istore_3() {
		bv.store(PrimitiveType.T_INT, 3);
	}

	@Override
	public void lstore_0() {
		bv.store(PrimitiveType.T_LONG, 0);
	}

	@Override
	public void lstore_1() {
		bv.store(PrimitiveType.T_LONG, 1);
	}

	@Override
	public void lstore_2() {
		bv.store(PrimitiveType.T_LONG, 2);
	}

	@Override
	public void lstore_3() {
		bv.store(PrimitiveType.T_LONG, 3);
	}

	@Override
	public void fstore_0() {
		bv.store(PrimitiveType.T_FLOAT, 0);
	}

	@Override
	public void fstore_1() {
		bv.store(PrimitiveType.T_FLOAT, 1);
	}

	@Override
	public void fstore_2() {
		bv.store(PrimitiveType.T_FLOAT, 2);
	}

	@Override
	public void fstore_3() {
		bv.store(PrimitiveType.T_FLOAT, 3);
	}

	@Override
	public void dstore_0() {
		bv.store(PrimitiveType.T_DOUBLE, 0);
	}

	@Override
	public void dstore_1() {
		bv.store(PrimitiveType.T_DOUBLE, 1);
	}

	@Override
	public void dstore_2() {
		bv.store(PrimitiveType.T_DOUBLE, 2);
	}

	@Override
	public void dstore_3() {
		bv.store(PrimitiveType.T_DOUBLE, 3);
	}

	@Override
	public void astore_0() {
		bv.store(OBJ, 0);
	}

	@Override
	public void astore_1() {
		bv.store(OBJ, 1);
	}

	@Override
	public void astore_2() {
		bv.store(OBJ, 2);
	}

	@Override
	public void astore_3() {
		bv.store(OBJ, 3);
	}

	@Override
	public void iastore() {
		bv.arrayStore(PrimitiveType.T_INT);
	}

	@Override
	public void lastore() {
		bv.arrayStore(PrimitiveType.T_LONG);
	}

	@Override
	public void fastore() {
		bv.arrayStore(PrimitiveType.T_FLOAT);
	}

	@Override
	public void dastore() {
		bv.arrayStore(PrimitiveType.T_DOUBLE);
	}

	@Override
	public void aastore() {
		bv.arrayStore(OBJ);
	}

	@Override
	public void bastore() {
		bv.arrayStore(PrimitiveType.T_BYTE);
	}

	@Override
	public void castore() {
		bv.arrayStore(PrimitiveType.T_CHAR);
	}

	@Override
	public void sastore() {
		bv.arrayStore(PrimitiveType.T_SHORT);
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
	public void iadd() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.ADD);
	}

	@Override
	public void ladd() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.ADD);
	}

	@Override
	public void fadd() {
		bv.mathOp(PrimitiveType.T_FLOAT, MathOperation.ADD);
	}

	@Override
	public void dadd() {
		bv.mathOp(PrimitiveType.T_DOUBLE, MathOperation.ADD);
	}

	@Override
	public void isub() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.SUB);
	}

	@Override
	public void lsub() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.SUB);
	}

	@Override
	public void fsub() {
		bv.mathOp(PrimitiveType.T_FLOAT, MathOperation.SUB);
	}

	@Override
	public void dsub() {
		bv.mathOp(PrimitiveType.T_DOUBLE, MathOperation.SUB);
	}

	@Override
	public void imul() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.MUL);
	}

	@Override
	public void lmul() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.MUL);
	}

	@Override
	public void fmul() {
		bv.mathOp(PrimitiveType.T_FLOAT, MathOperation.MUL);
	}

	@Override
	public void dmul() {
		bv.mathOp(PrimitiveType.T_DOUBLE, MathOperation.MUL);
	}

	@Override
	public void idiv() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.DIV);
	}

	@Override
	public void ldiv() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.DIV);
	}

	@Override
	public void fdiv() {
		bv.mathOp(PrimitiveType.T_FLOAT, MathOperation.DIV);
	}

	@Override
	public void ddiv() {
		bv.mathOp(PrimitiveType.T_DOUBLE, MathOperation.DIV);
	}

	@Override
	public void irem() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.REM);
	}

	@Override
	public void lrem() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.REM);
	}

	@Override
	public void frem() {
		bv.mathOp(PrimitiveType.T_FLOAT, MathOperation.REM);
	}

	@Override
	public void drem() {
		bv.mathOp(PrimitiveType.T_DOUBLE, MathOperation.REM);
	}

	@Override
	public void ineg() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.NEG);
	}

	@Override
	public void lneg() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.NEG);
	}

	@Override
	public void fneg() {
		bv.mathOp(PrimitiveType.T_FLOAT, MathOperation.NEG);
	}

	@Override
	public void dneg() {
		bv.mathOp(PrimitiveType.T_DOUBLE, MathOperation.NEG);
	}

	@Override
	public void ishl() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.SHL);
	}

	@Override
	public void lshl() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.SHL);
	}

	@Override
	public void ishr() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.SHR);
	}

	@Override
	public void lshr() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.SHR);
	}

	@Override
	public void iushr() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.USHR);
	}

	@Override
	public void lushr() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.USHR);
	}

	@Override
	public void iand() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.AND);
	}

	@Override
	public void land() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.AND);
	}

	@Override
	public void ior() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.OR);
	}

	@Override
	public void lor() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.OR);
	}

	@Override
	public void ixor() {
		bv.mathOp(PrimitiveType.T_INT, MathOperation.XOR);
	}

	@Override
	public void lxor() {
		bv.mathOp(PrimitiveType.T_LONG, MathOperation.XOR);
	}

	@Override
	public void iinc(int var, int value) {
		bv.increment(var, value);
	}

	@Override
	public void i2l() {
		bv.primitiveConversion(PrimitiveType.T_INT, PrimitiveType.T_LONG);
	}

	@Override
	public void i2f() {
		bv.primitiveConversion(PrimitiveType.T_INT, PrimitiveType.T_FLOAT);
	}

	@Override
	public void i2d() {
		bv.primitiveConversion(PrimitiveType.T_INT, PrimitiveType.T_DOUBLE);
	}

	@Override
	public void l2i() {
		bv.primitiveConversion(PrimitiveType.T_LONG, PrimitiveType.T_INT);
	}

	@Override
	public void l2f() {
		bv.primitiveConversion(PrimitiveType.T_LONG, PrimitiveType.T_FLOAT);
	}

	@Override
	public void l2d() {
		bv.primitiveConversion(PrimitiveType.T_LONG, PrimitiveType.T_DOUBLE);
	}

	@Override
	public void f2i() {
		bv.primitiveConversion(PrimitiveType.T_FLOAT, PrimitiveType.T_INT);
	}

	@Override
	public void f2l() {
		bv.primitiveConversion(PrimitiveType.T_FLOAT, PrimitiveType.T_LONG);
	}

	@Override
	public void f2d() {
		bv.primitiveConversion(PrimitiveType.T_FLOAT, PrimitiveType.T_DOUBLE);
	}

	@Override
	public void d2i() {
		bv.primitiveConversion(PrimitiveType.T_DOUBLE, PrimitiveType.T_INT);
	}

	@Override
	public void d2l() {
		bv.primitiveConversion(PrimitiveType.T_DOUBLE, PrimitiveType.T_LONG);
	}

	@Override
	public void d2f() {
		bv.primitiveConversion(PrimitiveType.T_DOUBLE, PrimitiveType.T_FLOAT);
	}

	@Override
	public void i2b() {
		bv.primitiveConversion(PrimitiveType.T_INT, PrimitiveType.T_BYTE);
	}

	@Override
	public void i2c() {
		bv.primitiveConversion(PrimitiveType.T_INT, PrimitiveType.T_CHAR);
	}

	@Override
	public void i2s() {
		bv.primitiveConversion(PrimitiveType.T_INT, PrimitiveType.T_SHORT);
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
	public void ifeq(int offset) {
		bv.unaryBranch(UnaryCondition.EQ, require(offset));
	}

	@Override
	public void ifne(int offset) {
		bv.unaryBranch(UnaryCondition.NE, require(offset));
	}

	@Override
	public void iflt(int offset) {
		bv.unaryBranch(UnaryCondition.LT, require(offset));
	}

	@Override
	public void ifge(int offset) {
		bv.unaryBranch(UnaryCondition.GE, require(offset));
	}

	@Override
	public void ifgt(int offset) {
		bv.unaryBranch(UnaryCondition.GT, require(offset));
	}

	@Override
	public void ifle(int offset) {
		bv.unaryBranch(UnaryCondition.LE, require(offset));
	}

	@Override
	public void if_icmpeq(int offset) {
		bv.binaryBranch(BinaryCondition.EQ, require(offset));
	}

	@Override
	public void if_icmpne(int offset) {
		bv.binaryBranch(BinaryCondition.NE, require(offset));
	}

	@Override
	public void if_icmplt(int offset) {
		bv.binaryBranch(BinaryCondition.LT, require(offset));
	}

	@Override
	public void if_icmpge(int offset) {
		bv.binaryBranch(BinaryCondition.GE, require(offset));
	}

	@Override
	public void if_icmpgt(int offset) {
		bv.binaryBranch(BinaryCondition.GT, require(offset));
	}

	@Override
	public void if_icmple(int offset) {
		bv.binaryBranch(BinaryCondition.LE, require(offset));
	}

	@Override
	public void if_acmpeq(int offset) {
		bv.binaryBranch(BinaryCondition.AEQ, require(offset));
	}

	@Override
	public void if_acmpne(int offset) {
		bv.binaryBranch(BinaryCondition.ANE, require(offset));
	}

	@Override
	public void goto_(int offset) {
		bv.goto_(require(offset));
	}

	@Override
	public void jsr(int offset) {
		bv.jsr(require(offset));
	}

	@Override
	public void ret(int index) {
		bv.ret(index);
	}

	@Override
	public void table_switch(int defaultBranchOffset, int low, int high, BinaryInput offsets) {
		List<Label> cases = new ArrayList<>();
		try {
			int count = 0;
			while (offsets.isReadable(1L)) {
				cases.add(require(offsets.readInt()));
				if (count++ == 16384) {
					throw new IllegalStateException("Too many switch cases");
				}
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
		bv.tableSwitch(require(defaultBranchOffset), low, high, cases);
	}

	@Override
	public void lookup_switch(int defaultBranchOffset, BinaryInput pairs) {
		int npairs = (int) (pairs.readableBytes() >>> 3);
		if (npairs > 16384) {
			throw new IllegalStateException("Too many cases " + npairs);
		}
		int[] keys = new int[npairs];
		Label[] cases = new Label[npairs];
		try {
			for (int i = 0; i < npairs; i++) {
				keys[i] = pairs.readInt();
				cases[i] = require(pairs.readInt());
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
		bv.lookupSwitch(require(defaultBranchOffset), keys, Arrays.asList(cases));
	}

	@Override
	public void ireturn() {
		bv.return_(PrimitiveType.T_INT);
	}

	@Override
	public void lreturn() {
		bv.return_(PrimitiveType.T_LONG);
	}

	@Override
	public void freturn() {
		bv.return_(PrimitiveType.T_FLOAT);
	}

	@Override
	public void dreturn() {
		bv.return_(PrimitiveType.T_DOUBLE);
	}

	@Override
	public void areturn() {
		bv.return_(OBJ);
	}

	@Override
	public void return_() {
		bv.return_(PrimitiveType.T_VOID);
	}

	@Override
	public void get_static(int fieldIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantFieldRef cst = constantPool.get(fieldIndex, Tag.FieldRef);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.getStatic(
				getInstanceType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				ClassType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value())
		);
	}

	@Override
	public void put_static(int fieldIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantFieldRef cst = constantPool.get(fieldIndex, Tag.FieldRef);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.putStatic(
				getInstanceType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				ClassType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value())
		);
	}

	@Override
	public void get_field(int fieldIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantFieldRef cst = constantPool.get(fieldIndex, Tag.FieldRef);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.getField(
				getInstanceType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				ClassType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value())
		);
	}

	@Override
	public void put_field(int fieldIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantFieldRef cst = constantPool.get(fieldIndex, Tag.FieldRef);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.putField(
				getInstanceType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				ClassType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value())
		);
	}

	@Override
	public void invoke_virtual(int methodIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantMemberRef cst = (ConstantMemberRef) constantPool.get(methodIndex);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.invokeVirtual(
				getObjectType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				MethodType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value())
		);
	}

	@Override
	public void invoke_special(int methodIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantMemberRef cst = (ConstantMemberRef) constantPool.get(methodIndex);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.invokeSpecial(
				getObjectType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				MethodType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value()),
				cst instanceof ConstantInterfaceMethodRef
		);
	}

	@Override
	public void invoke_static(int methodIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantMemberRef cst = (ConstantMemberRef) constantPool.get(methodIndex);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.invokeStatic(
				getObjectType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				MethodType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value()),
				cst instanceof ConstantInterfaceMethodRef
		);
	}

	@Override
	public void invoke_interface(int methodIndex, int count) {
		ConstantPool constantPool = symbolTable.constantPool();
		ConstantMemberRef cst = (ConstantMemberRef) constantPool.get(methodIndex);
		ConstantNameAndType nameAndType = constantPool.get(cst.nameAndTypeIndex(), Tag.NameAndType);
		bv.invokeInterface(
				getObjectType(cst.classIndex()),
				constantPool.get(nameAndType.nameIndex(), Tag.Utf8).value(),
				MethodType.ofDescriptor(constantPool.get(nameAndType.descriptorIndex(), Tag.Utf8).value())
		);
	}

	@Override
	public void invoke_dynamic(int invokeDynamicIndex) {
		bv.invokeDynamic(symbolTable.getInvokeDynamic(invokeDynamicIndex));
	}

	@Override
	public void new_(int classIndex) {
		bv.allocate(getInstanceType(classIndex));
	}

	@Override
	public void new_array(int type) {
		PrimitiveKind kind = PrimitiveKind.byId(type);
		Objects.requireNonNull(kind);
		bv.allocate(ArrayType.create(PrimitiveType.byKind(kind)));
	}

	@Override
	public void anew_array(int classIndex) {
		bv.allocate(ArrayType.create(getObjectType(classIndex)));
	}

	@Override
	public void array_length() {
		bv.arrayLength();
	}

	@Override
	public void athrow() {
		bv.athrow();
	}

	@Override
	public void check_cast(int classIndex) {
		bv.checkCast(getObjectType(classIndex));
	}

	@Override
	public void instanceof_(int classIndex) {
		bv.instanceOf(getObjectType(classIndex));
	}

	@Override
	public void monitor_enter() {
		bv.monitorEnter();
	}

	@Override
	public void monitor_exit() {
		bv.monitorExit();
	}

	@Override
	public void wide(int opcode, int op1, int op2) {
		dev.xdark.classfile.representation.bytecode.BytecodeVisitor bv = this.bv;
		if (opcode == IINC) {
			bv.increment(op1, op2);
		} else if (opcode == RET) {
			bv.ret(op1);
		} else {
			switch (opcode) {
				case ILOAD:
					iload(op1);
					break;
				case FLOAD:
					fload(op1);
					break;
				case ALOAD:
					aload(op1);
					break;
				case LLOAD:
					lload(op1);
					break;
				case DLOAD:
					dload(op1);
					break;
				case ISTORE:
					istore(op1);
					break;
				case FSTORE:
					fstore(op1);
					break;
				case ASTORE:
					astore(op1);
					break;
				case LSTORE:
					lstore(op1);
					break;
				case DSTORE:
					dstore(op1);
					break;
				default:
					throw new IllegalStateException(String.format("Unknown wide opcode %d", opcode));
			}
		}
	}

	@Override
	public void multi_anew_array(int classIndex, int dimensions) {
		bv.allocate(getObjectType(classIndex));
	}

	@Override
	public void ifnull(int offset) {
		bv.unaryBranch(UnaryCondition.NULL, require(offset));
	}

	@Override
	public void ifnonnull(int offset) {
		bv.unaryBranch(UnaryCondition.NONNULL, require(offset));
	}

	@Override
	public void goto_w(int offset) {
		bv.goto_(require(offset));
	}

	@Override
	public void jsr_w(int offset) {
		bv.jsr(require(offset));
	}

	private Label require(int offset) {
		int pos = tracker.position() + offset;
		Label l = labels.get(pos);
		if (l == null) {
			throw new IllegalStateException(String.format("Label not found at %d", pos));
		}
		return l;
	}

	private ObjectType getObjectType(int classIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		return ObjectType.ofInternalName(
				constantPool.get(constantPool.get(classIndex, Tag.Class).nameIndex(), Tag.Utf8).value()
		);
	}

	private InstanceType getInstanceType(int classIndex) {
		ConstantPool constantPool = symbolTable.constantPool();
		return InstanceType.ofInternalName(
				constantPool.get(constantPool.get(classIndex, Tag.Class).nameIndex(), Tag.Utf8).value()
		);
	}
}
