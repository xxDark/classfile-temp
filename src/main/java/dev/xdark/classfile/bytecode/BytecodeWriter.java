package dev.xdark.classfile.bytecode;

import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.io.BinaryOutput;

import java.io.IOException;
import java.io.UncheckedIOException;

import static dev.xdark.classfile.bytecode.Bytecodes.*;

public final class BytecodeWriter implements BytecodeVisitor {
	private final BinaryOutput output;

	public BytecodeWriter(BinaryOutput output) {
		this.output = output;
	}

	@Override
	public void nop() {
		putByte(NOP);
	}

	@Override
	public void aconst_null() {
		putByte(ACONST_NULL);
	}

	@Override
	public void iconst(int n) {
		int op = ICONST_0 + n;
		if (op < ICONST_M1 || op > ICONST_5) {
			throw new IllegalArgumentException(String.format("Illegal iconst %d", n));
		}
		putByte(op);
	}

	@Override
	public void lconst_0() {
		putByte(LCONST_0);
	}

	@Override
	public void lconst_1() {
		putByte(LCONST_1);
	}

	@Override
	public void fconst_0() {
		putByte(FCONST_0);
	}

	@Override
	public void fconst_1() {
		putByte(FCONST_1);
	}

	@Override
	public void fconst_2() {
		putByte(FCONST_2);
	}

	@Override
	public void dconst_0() {
		putByte(DCONST_0);
	}

	@Override
	public void dconst_1() {
		putByte(DCONST_1);
	}

	@Override
	public void bipush(int value) {
		// putShort(value | BIPUSH << 8);
		putByte(BIPUSH);
		putByte(value);
	}

	@Override
	public void sipush(int value) {
		putByte(SIPUSH);
		putShort(value);
	}

	@Override
	public void ldc(int index) {
		ensureInRange(index, 0, 255);
		putByte(LDC);
		putByte(index);
	}

	@Override
	public void ldc_w(int index) {
		ensureInU2Range(index);
		putByte(LDC_W);
		putShort(index);
	}

	@Override
	public void ldc2_w(int index) {
		ensureInU2Range(index);
		putByte(LDC2_W);
		putShort(index);
	}

	@Override
	public void iload(int index) {
		ensureInRange(index, 0, 255);
		putByte(ILOAD);
		putByte(index);
	}

	@Override
	public void lload(int index) {
		ensureInRange(index, 0, 255);
		putByte(LLOAD);
		putByte(index);
	}

	@Override
	public void fload(int index) {
		ensureInRange(index, 0, 255);
		putByte(FLOAD);
		putByte(index);
	}

	@Override
	public void dload(int index) {
		ensureInRange(index, 0, 255);
		putByte(DLOAD);
		putByte(index);
	}

	@Override
	public void aload(int index) {
		ensureInRange(index, 0, 255);
		putByte(ALOAD);
		putByte(index);
	}

	@Override
	public void iload_0() {
		putByte(ILOAD_0);
	}

	@Override
	public void iload_1() {
		putByte(ILOAD_1);
	}

	@Override
	public void iload_2() {
		putByte(ILOAD_2);
	}

	@Override
	public void iload_3() {
		putByte(ILOAD_3);
	}

	@Override
	public void lload_0() {
		putByte(LLOAD_0);
	}

	@Override
	public void lload_1() {
		putByte(LLOAD_1);
	}

	@Override
	public void lload_2() {
		putByte(LLOAD_2);
	}

	@Override
	public void lload_3() {
		putByte(LLOAD_3);
	}

	@Override
	public void fload_0() {
		putByte(FLOAD_0);
	}

	@Override
	public void fload_1() {
		putByte(FLOAD_1);
	}

	@Override
	public void fload_2() {
		putByte(FLOAD_2);
	}

	@Override
	public void fload_3() {
		putByte(FLOAD_3);
	}

	@Override
	public void dload_0() {
		putByte(DLOAD_0);
	}

	@Override
	public void dload_1() {
		putByte(DLOAD_1);
	}

	@Override
	public void dload_2() {
		putByte(DLOAD_2);
	}

	@Override
	public void dload_3() {
		putByte(DLOAD_3);
	}

	@Override
	public void aload_0() {
		putByte(ALOAD_0);
	}

	@Override
	public void aload_1() {
		putByte(ALOAD_1);
	}

	@Override
	public void aload_2() {
		putByte(ALOAD_2);
	}

	@Override
	public void aload_3() {
		putByte(ALOAD_3);
	}

	@Override
	public void iaload() {
		putByte(IALOAD);
	}

	@Override
	public void laload() {
		putByte(LALOAD);
	}

	@Override
	public void faload() {
		putByte(FALOAD);
	}

	@Override
	public void daload() {
		putByte(DALOAD);
	}

	@Override
	public void aaload() {
		putByte(AALOAD);
	}

	@Override
	public void baload() {
		putByte(BALOAD);
	}

	@Override
	public void caload() {
		putByte(CALOAD);
	}

	@Override
	public void saload() {
		putByte(SALOAD);
	}

	@Override
	public void istore(int index) {
		ensureInRange(index, 0, 255);
		putByte(ISTORE);
		putByte(index);
	}

	@Override
	public void lstore(int index) {
		ensureInRange(index, 0, 255);
		putByte(LSTORE);
		putByte(index);
	}

	@Override
	public void fstore(int index) {
		ensureInRange(index, 0, 255);
		putByte(FSTORE);
		putByte(index);
	}

	@Override
	public void dstore(int index) {
		ensureInRange(index, 0, 255);
		putByte(DSTORE);
		putByte(index);
	}

	@Override
	public void astore(int index) {
		ensureInRange(index, 0, 255);
		putByte(ASTORE);
		putByte(index);
	}

	@Override
	public void istore_0() {
		putByte(ISTORE_0);
	}

	@Override
	public void istore_1() {
		putByte(ISTORE_1);
	}

	@Override
	public void istore_2() {
		putByte(ISTORE_2);
	}

	@Override
	public void istore_3() {
		putByte(ISTORE_3);
	}

	@Override
	public void lstore_0() {
		putByte(LSTORE_0);
	}

	@Override
	public void lstore_1() {
		putByte(LSTORE_1);
	}

	@Override
	public void lstore_2() {
		putByte(LSTORE_2);
	}

	@Override
	public void lstore_3() {
		putByte(LSTORE_3);
	}

	@Override
	public void fstore_0() {
		putByte(FSTORE_0);
	}

	@Override
	public void fstore_1() {
		putByte(FSTORE_1);
	}

	@Override
	public void fstore_2() {
		putByte(FSTORE_2);
	}

	@Override
	public void fstore_3() {
		putByte(FSTORE_3);
	}

	@Override
	public void dstore_0() {
		putByte(DSTORE_0);
	}

	@Override
	public void dstore_1() {
		putByte(DSTORE_1);
	}

	@Override
	public void dstore_2() {
		putByte(DSTORE_2);
	}

	@Override
	public void dstore_3() {
		putByte(DSTORE_3);
	}

	@Override
	public void astore_0() {
		putByte(ASTORE_0);
	}

	@Override
	public void astore_1() {
		putByte(ASTORE_1);
	}

	@Override
	public void astore_2() {
		putByte(ASTORE_2);
	}

	@Override
	public void astore_3() {
		putByte(ASTORE_3);
	}

	@Override
	public void iastore() {
		putByte(IASTORE);
	}

	@Override
	public void lastore() {
		putByte(LASTORE);
	}

	@Override
	public void fastore() {
		putByte(FASTORE);
	}

	@Override
	public void dastore() {
		putByte(DASTORE);
	}

	@Override
	public void aastore() {
		putByte(AASTORE);
	}

	@Override
	public void bastore() {
		putByte(BASTORE);
	}

	@Override
	public void castore() {
		putByte(CASTORE);
	}

	@Override
	public void sastore() {
		putByte(SASTORE);
	}

	@Override
	public void pop() {
		putByte(POP);
	}

	@Override
	public void pop2() {
		putByte(POP2);
	}

	@Override
	public void dup() {
		putByte(DUP);
	}

	@Override
	public void dup_x1() {
		putByte(DUP_X1);
	}

	@Override
	public void dup_x2() {
		putByte(DUP_X2);
	}

	@Override
	public void dup2() {
		putByte(DUP2);
	}

	@Override
	public void dup2_x1() {
		putByte(DUP2_X1);
	}

	@Override
	public void dup2_x2() {
		putByte(DUP2_X2);
	}

	@Override
	public void swap() {
		putByte(SWAP);
	}

	@Override
	public void iadd() {
		putByte(IADD);
	}

	@Override
	public void ladd() {
		putByte(LADD);
	}

	@Override
	public void fadd() {
		putByte(FADD);
	}

	@Override
	public void dadd() {
		putByte(DADD);
	}

	@Override
	public void isub() {
		putByte(ISUB);
	}

	@Override
	public void lsub() {
		putByte(LSUB);
	}

	@Override
	public void fsub() {
		putByte(FSUB);
	}

	@Override
	public void dsub() {
		putByte(DSUB);
	}

	@Override
	public void imul() {
		putByte(IMUL);
	}

	@Override
	public void lmul() {
		putByte(LMUL);
	}

	@Override
	public void fmul() {
		putByte(FMUL);
	}

	@Override
	public void dmul() {
		putByte(DMUL);
	}

	@Override
	public void idiv() {
		putByte(IDIV);
	}

	@Override
	public void ldiv() {
		putByte(LDIV);
	}

	@Override
	public void fdiv() {
		putByte(FDIV);
	}

	@Override
	public void ddiv() {
		putByte(DDIV);
	}

	@Override
	public void irem() {
		putByte(IREM);
	}

	@Override
	public void lrem() {
		putByte(LREM);
	}

	@Override
	public void frem() {
		putByte(FREM);
	}

	@Override
	public void drem() {
		putByte(DREM);
	}

	@Override
	public void ineg() {
		putByte(INEG);
	}

	@Override
	public void lneg() {
		putByte(LNEG);
	}

	@Override
	public void fneg() {
		putByte(FNEG);
	}

	@Override
	public void dneg() {
		putByte(DNEG);
	}

	@Override
	public void ishl() {
		putByte(ISHL);
	}

	@Override
	public void lshl() {
		putByte(LSHL);
	}

	@Override
	public void ishr() {
		putByte(ISHR);
	}

	@Override
	public void lshr() {
		putByte(LSHR);
	}

	@Override
	public void iushr() {
		putByte(IUSHR);
	}

	@Override
	public void lushr() {
		putByte(LUSHR);
	}

	@Override
	public void iand() {
		putByte(IAND);
	}

	@Override
	public void land() {
		putByte(LAND);
	}

	@Override
	public void ior() {
		putByte(IOR);
	}

	@Override
	public void lor() {
		putByte(LOR);
	}

	@Override
	public void ixor() {
		putByte(IXOR);
	}

	@Override
	public void lxor() {
		putByte(LXOR);
	}

	@Override
	public void iinc(int var, int value) {
		ensureInRange(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
		putByte(IINC);
		putByte(var);
	}

	@Override
	public void i2l() {
		putByte(I2L);
	}

	@Override
	public void i2f() {
		putByte(I2F);
	}

	@Override
	public void i2d() {
		putByte(I2D);
	}

	@Override
	public void l2i() {
		putByte(L2I);
	}

	@Override
	public void l2f() {
		putByte(L2F);
	}

	@Override
	public void l2d() {
		putByte(L2D);
	}

	@Override
	public void f2i() {
		putByte(F2I);
	}

	@Override
	public void f2l() {
		putByte(F2L);
	}

	@Override
	public void f2d() {
		putByte(F2D);
	}

	@Override
	public void d2i() {
		putByte(D2I);
	}

	@Override
	public void d2l() {
		putByte(D2L);
	}

	@Override
	public void d2f() {
		putByte(D2F);
	}

	@Override
	public void i2b() {
		putByte(I2B);
	}

	@Override
	public void i2c() {
		putByte(I2C);
	}

	@Override
	public void i2s() {
		putByte(I2S);
	}

	@Override
	public void lcmp() {
		putByte(LCMP);
	}

	@Override
	public void fcmpl() {
		putByte(FCMPL);
	}

	@Override
	public void fcmpg() {
		putByte(FCMPG);
	}

	@Override
	public void dcmpl() {
		putByte(DCMPL);
	}

	@Override
	public void dcmpg() {
		putByte(DCMPG);
	}

	@Override
	public void ifeq(int offset) {
		ensureInI2Range(offset);
		putByte(IFEQ);
		putShort(offset);
	}

	@Override
	public void ifne(int offset) {
		ensureInI2Range(offset);
		putByte(IFNE);
		putShort(offset);
	}

	@Override
	public void iflt(int offset) {
		ensureInI2Range(offset);
		putByte(IFLT);
		putShort(offset);
	}

	@Override
	public void ifge(int offset) {
		ensureInI2Range(offset);
		putByte(IFGE);
		putShort(offset);
	}

	@Override
	public void ifgt(int offset) {
		ensureInI2Range(offset);
		putByte(IFGT);
		putShort(offset);
	}

	@Override
	public void ifle(int offset) {
		ensureInI2Range(offset);
		putByte(IFLE);
		putShort(offset);
	}

	@Override
	public void if_icmpeq(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ICMPEQ);
		putShort(offset);
	}

	@Override
	public void if_icmpne(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ICMPNE);
		putShort(offset);
	}

	@Override
	public void if_icmplt(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ICMPLT);
		putShort(offset);
	}

	@Override
	public void if_icmpge(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ICMPGE);
		putShort(offset);
	}

	@Override
	public void if_icmpgt(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ICMPGT);
		putShort(offset);
	}

	@Override
	public void if_icmple(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ICMPLE);
		putShort(offset);
	}

	@Override
	public void if_acmpeq(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ACMPEQ);
		putShort(offset);
	}

	@Override
	public void if_acmpne(int offset) {
		ensureInI2Range(offset);
		putByte(IF_ACMPNE);
		putShort(offset);
	}

	@Override
	public void goto_(int offset) {
		ensureInI2Range(offset);
		putByte(GOTO);
		putShort(offset);
	}

	@Override
	public void jsr(int offset) {
		ensureInI2Range(offset);
		putByte(JSR);
		putShort(offset);
	}

	@Override
	public void ret(int index) {
		ensureInRange(index, 0, 255);
		putByte(RET);
		putByte(index);
	}

	@Override
	public void table_switch(int defaultBranchOffset, int low, int high, BinaryInput offsets) {
		putByte(TABLESWITCH);
		BinaryOutput out = output;
		long pos = out.position();
		out.position(pos + (4L - pos & 3L));
		out.writeInt(defaultBranchOffset);
		out.writeInt(low);
		out.writeInt(high);
		while (offsets.isReadable(1L)) {
			out.writeInt(offsets.readInt());
		}
	}

	@Override
	public void lookup_switch(int defaultBranchOffset, BinaryInput pairs) {
		putByte(LOOKUPSWITCH);
		BinaryOutput out = output;
		long pos = out.position();
		out.position(pos + (4L - pos & 3L));
		out.writeInt(defaultBranchOffset);
		out.writeInt((int) (pairs.readableBytes() >>> 3));
		while (pairs.isReadable(1L)) {
			out.writeInt(pairs.readInt());
		}
	}

	@Override
	public void ireturn() {
		putByte(IRETURN);
	}

	@Override
	public void lreturn() {
		putByte(LRETURN);
	}

	@Override
	public void freturn() {
		putByte(FRETURN);
	}

	@Override
	public void dreturn() {
		putByte(DRETURN);
	}

	@Override
	public void areturn() {
		putByte(ARETURN);
	}

	@Override
	public void return_() {
		putByte(RETURN);
	}

	@Override
	public void get_static(int fieldIndex) {
		ensureInU2Range(fieldIndex);
		putByte(GETSTATIC);
		putShort(fieldIndex);
	}

	@Override
	public void put_static(int fieldIndex) {
		ensureInU2Range(fieldIndex);
		putByte(PUTSTATIC);
		putShort(fieldIndex);
	}

	@Override
	public void get_field(int fieldIndex) {
		ensureInU2Range(fieldIndex);
		putByte(GETFIELD);
		putShort(fieldIndex);
	}

	@Override
	public void put_field(int fieldIndex) {
		ensureInU2Range(fieldIndex);
		putByte(PUTFIELD);
		putShort(fieldIndex);
	}

	@Override
	public void invoke_virtual(int methodIndex) {
		ensureInU2Range(methodIndex);
		putByte(INVOKEVIRTUAL);
		putShort(methodIndex);
	}

	@Override
	public void invoke_special(int methodIndex) {
		ensureInU2Range(methodIndex);
		putByte(INVOKESPECIAL);
		putShort(methodIndex);
	}

	@Override
	public void invoke_static(int methodIndex) {
		ensureInU2Range(methodIndex);
		putByte(INVOKESTATIC);
		putShort(methodIndex);
	}

	@Override
	public void invoke_interface(int methodIndex, int count) {
		ensureInU2Range(methodIndex);
		putByte(INVOKEINTERFACE);
		putShort(methodIndex);
		putByte(count);
		putByte(0);
	}

	@Override
	public void invoke_dynamic(int invokeDynamicIndex) {
		ensureInU2Range(invokeDynamicIndex);
		putByte(INVOKEDYNAMIC);
		putShort(invokeDynamicIndex);
		putShort(0);
	}

	@Override
	public void new_(int classIndex) {
		ensureInU2Range(classIndex);
		putByte(NEW);
		putShort(classIndex);
	}

	@Override
	public void new_array(int type) {
		putByte(NEWARRAY);
		putByte(type);
	}

	@Override
	public void anew_array(int classIndex) {
		ensureInU2Range(classIndex);
		putByte(ANEWARRAY);
		putShort(classIndex);
	}

	@Override
	public void array_length() {
		putByte(ARRAYLENGTH);
	}

	@Override
	public void athrow() {
		putByte(ATHROW);
	}

	@Override
	public void check_cast(int classIndex) {
		ensureInU2Range(classIndex);
		putByte(CHECKCAST);
		putShort(classIndex);
	}

	@Override
	public void instanceof_(int classIndex) {
		ensureInU2Range(classIndex);
		putByte(INSTANCEOF);
		putShort(classIndex);
	}

	@Override
	public void monitor_enter() {
		putByte(MONITORENTER);
	}

	@Override
	public void monitor_exit() {
		putByte(MONITOREXIT);
	}

	@Override
	public void wide(int opcode, int op1, int op2) {
		putByte(WIDE);
		if (opcode == IINC) {
			ensureInU2Range(op1);
			putByte(IINC);
			putShort(op1);
			putShort(op2);
			return;
		}
		switch (opcode) {
			case ILOAD:
			case FLOAD:
			case ALOAD:
			case LLOAD:
			case DLOAD:
			case ISTORE:
			case FSTORE:
			case ASTORE:
			case LSTORE:
			case DSTORE:
			case RET:
				ensureInU2Range(op1);
				putByte(opcode);
				putShort(op1);
				return;
		}
		throw new IllegalStateException(String.format("Illegal wide opcode: %d", opcode));
	}

	@Override
	public void multi_anew_array(int classIndex, int dimensions) {
		ensureInU2Range(classIndex);
		ensureInRange(dimensions, 0, 255);
		putByte(MULTIANEWARRAY);
		putShort(classIndex);
		putByte(dimensions);
	}

	@Override
	public void ifnull(int offset) {
		ensureInI2Range(offset);
		putByte(IFNULL);
		putShort(offset);
	}

	@Override
	public void ifnonnull(int offset) {
		ensureInI2Range(offset);
		putByte(IFNONNULL);
		putShort(offset);
	}

	@Override
	public void goto_w(int offset) {
		putByte(GOTO_W);
		putInt(offset);
	}

	@Override
	public void jsr_w(int offset) {
		putByte(JSR_W);
		putInt(offset);
	}

	private void putByte(int op) {
		output.writeByte(op);
	}

	private void putShort(int op) {
		output.writeShort(op);
	}

	private void putInt(int op) {
		output.writeInt(op);
	}

	private static void ensureInRange(int value, int min, int max) {
		if (value < min || value > max) {
			throw new IllegalArgumentException(String.format("Value out of range: %d < %d < %d", min, value, max));
		}
	}

	private static void ensureInU2Range(int value) {
		ensureInRange(value, 0, 65535);
	}

	private static void ensureInI2Range(int value) {
		ensureInRange(value, Short.MIN_VALUE, Short.MAX_VALUE);
	}
}
