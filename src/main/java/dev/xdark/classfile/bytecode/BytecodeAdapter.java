package dev.xdark.classfile.bytecode;

import dev.xdark.classfile.io.BinaryInput;

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
	public void aconst_null() {
		bv.aconst_null();
	}

	@Override
	public void iconst(int n) {
		bv.iconst(n);
	}

	@Override
	public void lconst_0() {
		bv.lconst_0();
	}

	@Override
	public void lconst_1() {
		bv.lconst_1();
	}

	@Override
	public void fconst_0() {
		bv.fconst_0();
	}

	@Override
	public void fconst_1() {
		bv.fconst_1();
	}

	@Override
	public void fconst_2() {
		bv.fconst_2();
	}

	@Override
	public void dconst_0() {
		bv.dconst_0();
	}

	@Override
	public void dconst_1() {
		bv.dconst_1();
	}

	@Override
	public void bipush(int value) {
		bv.bipush(value);
	}

	@Override
	public void sipush(int value) {
		bv.sipush(value);
	}

	@Override
	public void ldc(int index) {
		bv.ldc(index);
	}

	@Override
	public void ldc_w(int index) {
		bv.ldc_w(index);
	}

	@Override
	public void ldc2_w(int index) {
		bv.ldc2_w(index);
	}

	@Override
	public void iload(int index) {
		bv.iload(index);
	}

	@Override
	public void lload(int index) {
		bv.lload(index);
	}

	@Override
	public void fload(int index) {
		bv.fload(index);
	}

	@Override
	public void dload(int index) {
		bv.dload(index);
	}

	@Override
	public void aload(int index) {
		bv.aload(index);
	}

	@Override
	public void iload_0() {
		bv.iload_0();
	}

	@Override
	public void iload_1() {
		bv.iload_1();
	}

	@Override
	public void iload_2() {
		bv.iload_2();
	}

	@Override
	public void iload_3() {
		bv.iload_3();
	}

	@Override
	public void lload_0() {
		bv.lload_0();
	}

	@Override
	public void lload_1() {
		bv.lload_1();
	}

	@Override
	public void lload_2() {
		bv.lload_2();
	}

	@Override
	public void lload_3() {
		bv.lload_3();
	}

	@Override
	public void fload_0() {
		bv.fload_0();
	}

	@Override
	public void fload_1() {
		bv.fload_1();
	}

	@Override
	public void fload_2() {
		bv.fload_2();
	}

	@Override
	public void fload_3() {
		bv.fload_3();
	}

	@Override
	public void dload_0() {
		bv.dload_0();
	}

	@Override
	public void dload_1() {
		bv.dload_1();
	}

	@Override
	public void dload_2() {
		bv.dload_2();
	}

	@Override
	public void dload_3() {
		bv.dload_3();
	}

	@Override
	public void aload_0() {
		bv.aload_0();
	}

	@Override
	public void aload_1() {
		bv.aload_1();
	}

	@Override
	public void aload_2() {
		bv.aload_2();
	}

	@Override
	public void aload_3() {
		bv.aload_3();
	}

	@Override
	public void iaload() {
		bv.iaload();
	}

	@Override
	public void laload() {
		bv.laload();
	}

	@Override
	public void faload() {
		bv.faload();
	}

	@Override
	public void daload() {
		bv.daload();
	}

	@Override
	public void aaload() {
		bv.aaload();
	}

	@Override
	public void baload() {
		bv.baload();
	}

	@Override
	public void caload() {
		bv.caload();
	}

	@Override
	public void saload() {
		bv.saload();
	}

	@Override
	public void istore(int index) {
		bv.istore(index);
	}

	@Override
	public void lstore(int index) {
		bv.lstore(index);
	}

	@Override
	public void fstore(int index) {
		bv.fstore(index);
	}

	@Override
	public void dstore(int index) {
		bv.dstore(index);
	}

	@Override
	public void astore(int index) {
		bv.astore(index);
	}

	@Override
	public void istore_0() {
		bv.istore_0();
	}

	@Override
	public void istore_1() {
		bv.istore_1();
	}

	@Override
	public void istore_2() {
		bv.istore_2();
	}

	@Override
	public void istore_3() {
		bv.istore_3();
	}

	@Override
	public void lstore_0() {
		bv.lstore_0();
	}

	@Override
	public void lstore_1() {
		bv.lstore_1();
	}

	@Override
	public void lstore_2() {
		bv.lstore_2();
	}

	@Override
	public void lstore_3() {
		bv.lstore_3();
	}

	@Override
	public void fstore_0() {
		bv.fstore_0();
	}

	@Override
	public void fstore_1() {
		bv.fstore_1();
	}

	@Override
	public void fstore_2() {
		bv.fstore_2();
	}

	@Override
	public void fstore_3() {
		bv.fstore_3();
	}

	@Override
	public void dstore_0() {
		bv.dstore_0();
	}

	@Override
	public void dstore_1() {
		bv.dstore_1();
	}

	@Override
	public void dstore_2() {
		bv.dstore_2();
	}

	@Override
	public void dstore_3() {
		bv.dstore_3();
	}

	@Override
	public void astore_0() {
		bv.astore_0();
	}

	@Override
	public void astore_1() {
		bv.astore_1();
	}

	@Override
	public void astore_2() {
		bv.astore_2();
	}

	@Override
	public void astore_3() {
		bv.astore_3();
	}

	@Override
	public void iastore() {
		bv.iastore();
	}

	@Override
	public void lastore() {
		bv.lastore();
	}

	@Override
	public void fastore() {
		bv.fastore();
	}

	@Override
	public void dastore() {
		bv.dastore();
	}

	@Override
	public void aastore() {
		bv.aastore();
	}

	@Override
	public void bastore() {
		bv.bastore();
	}

	@Override
	public void castore() {
		bv.castore();
	}

	@Override
	public void sastore() {
		bv.sastore();
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
		bv.iadd();
	}

	@Override
	public void ladd() {
		bv.ladd();
	}

	@Override
	public void fadd() {
		bv.fadd();
	}

	@Override
	public void dadd() {
		bv.dadd();
	}

	@Override
	public void isub() {
		bv.isub();
	}

	@Override
	public void lsub() {
		bv.lsub();
	}

	@Override
	public void fsub() {
		bv.fsub();
	}

	@Override
	public void dsub() {
		bv.dsub();
	}

	@Override
	public void imul() {
		bv.imul();
	}

	@Override
	public void lmul() {
		bv.lmul();
	}

	@Override
	public void fmul() {
		bv.fmul();
	}

	@Override
	public void dmul() {
		bv.dmul();
	}

	@Override
	public void idiv() {
		bv.idiv();
	}

	@Override
	public void ldiv() {
		bv.ldiv();
	}

	@Override
	public void fdiv() {
		bv.fdiv();
	}

	@Override
	public void ddiv() {
		bv.ddiv();
	}

	@Override
	public void irem() {
		bv.irem();
	}

	@Override
	public void lrem() {
		bv.lrem();
	}

	@Override
	public void frem() {
		bv.frem();
	}

	@Override
	public void drem() {
		bv.drem();
	}

	@Override
	public void ineg() {
		bv.ineg();
	}

	@Override
	public void lneg() {
		bv.lneg();
	}

	@Override
	public void fneg() {
		bv.fneg();
	}

	@Override
	public void dneg() {
		bv.dneg();
	}

	@Override
	public void ishl() {
		bv.ishl();
	}

	@Override
	public void lshl() {
		bv.lshl();
	}

	@Override
	public void ishr() {
		bv.ishr();
	}

	@Override
	public void lshr() {
		bv.lshr();
	}

	@Override
	public void iushr() {
		bv.iushr();
	}

	@Override
	public void lushr() {
		bv.lushr();
	}

	@Override
	public void iand() {
		bv.iand();
	}

	@Override
	public void land() {
		bv.land();
	}

	@Override
	public void ior() {
		bv.ior();
	}

	@Override
	public void lor() {
		bv.lor();
	}

	@Override
	public void ixor() {
		bv.ixor();
	}

	@Override
	public void lxor() {
		bv.lxor();
	}

	@Override
	public void iinc(int var, int value) {
		bv.iinc(var, value);
	}

	@Override
	public void i2l() {
		bv.i2l();
	}

	@Override
	public void i2f() {
		bv.i2f();
	}

	@Override
	public void i2d() {
		bv.i2d();
	}

	@Override
	public void l2i() {
		bv.l2i();
	}

	@Override
	public void l2f() {
		bv.l2f();
	}

	@Override
	public void l2d() {
		bv.l2d();
	}

	@Override
	public void f2i() {
		bv.f2i();
	}

	@Override
	public void f2l() {
		bv.f2l();
	}

	@Override
	public void f2d() {
		bv.f2d();
	}

	@Override
	public void d2i() {
		bv.d2i();
	}

	@Override
	public void d2l() {
		bv.d2l();
	}

	@Override
	public void d2f() {
		bv.d2f();
	}

	@Override
	public void i2b() {
		bv.i2b();
	}

	@Override
	public void i2c() {
		bv.i2c();
	}

	@Override
	public void i2s() {
		bv.i2s();
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
		bv.ifeq(offset);
	}

	@Override
	public void ifne(int offset) {
		bv.ifne(offset);
	}

	@Override
	public void iflt(int offset) {
		bv.iflt(offset);
	}

	@Override
	public void ifge(int offset) {
		bv.ifge(offset);
	}

	@Override
	public void ifgt(int offset) {
		bv.ifgt(offset);
	}

	@Override
	public void ifle(int offset) {
		bv.ifle(offset);
	}

	@Override
	public void if_icmpeq(int offset) {
		bv.if_icmpeq(offset);
	}

	@Override
	public void if_icmpne(int offset) {
		bv.if_icmpne(offset);
	}

	@Override
	public void if_icmplt(int offset) {
		bv.if_icmplt(offset);
	}

	@Override
	public void if_icmpge(int offset) {
		bv.if_icmpge(offset);
	}

	@Override
	public void if_icmpgt(int offset) {
		bv.if_icmpgt(offset);
	}

	@Override
	public void if_icmple(int offset) {
		bv.if_icmple(offset);
	}

	@Override
	public void if_acmpeq(int offset) {
		bv.if_acmpeq(offset);
	}

	@Override
	public void if_acmpne(int offset) {
		bv.if_acmpne(offset);
	}

	@Override
	public void goto_(int offset) {
		bv.goto_(offset);
	}

	@Override
	public void jsr(int offset) {
		bv.jsr(offset);
	}

	@Override
	public void ret(int index) {
		bv.ret(index);
	}

	@Override
	public void table_switch(int defaultBranchOffset, int low, int high, BinaryInput offsets) {
		bv.table_switch(defaultBranchOffset, low, high, offsets);
	}

	@Override
	public void lookup_switch(int defaultBranchOffset, BinaryInput pairs) {
		bv.lookup_switch(defaultBranchOffset, pairs);
	}

	@Override
	public void ireturn() {
		bv.ireturn();
	}

	@Override
	public void lreturn() {
		bv.lreturn();
	}

	@Override
	public void freturn() {
		bv.freturn();
	}

	@Override
	public void dreturn() {
		bv.dreturn();
	}

	@Override
	public void areturn() {
		bv.areturn();
	}

	@Override
	public void return_() {
		bv.return_();
	}

	@Override
	public void get_static(int fieldIndex) {
		bv.get_static(fieldIndex);
	}

	@Override
	public void put_static(int fieldIndex) {
		bv.put_static(fieldIndex);
	}

	@Override
	public void get_field(int fieldIndex) {
		bv.get_field(fieldIndex);
	}

	@Override
	public void put_field(int fieldIndex) {
		bv.put_field(fieldIndex);
	}

	@Override
	public void invoke_virtual(int methodIndex) {
		bv.invoke_virtual(methodIndex);
	}

	@Override
	public void invoke_special(int methodIndex) {
		bv.invoke_special(methodIndex);
	}

	@Override
	public void invoke_static(int methodIndex) {
		bv.invoke_static(methodIndex);
	}

	@Override
	public void invoke_interface(int methodIndex, int count) {
		bv.invoke_interface(methodIndex, count);
	}

	@Override
	public void invoke_dynamic(int invokeDynamicIndex) {
		bv.invoke_dynamic(invokeDynamicIndex);
	}

	@Override
	public void new_(int classIndex) {
		bv.new_(classIndex);
	}

	@Override
	public void new_array(int type) {
		bv.new_array(type);
	}

	@Override
	public void anew_array(int classIndex) {
		bv.anew_array(classIndex);
	}

	@Override
	public void array_length() {
		bv.array_length();
	}

	@Override
	public void athrow() {
		bv.athrow();
	}

	@Override
	public void check_cast(int classIndex) {
		bv.check_cast(classIndex);
	}

	@Override
	public void instanceof_(int classIndex) {
		bv.instanceof_(classIndex);
	}

	@Override
	public void monitor_enter() {
		bv.monitor_enter();
	}

	@Override
	public void monitor_exit() {
		bv.monitor_exit();
	}

	@Override
	public void wide(int opcode, int op1, int op2) {
		bv.wide(opcode, op1, op2);
	}

	@Override
	public void multi_anew_array(int classIndex, int dimensions) {
		bv.multi_anew_array(classIndex, dimensions);
	}

	@Override
	public void ifnull(int offset) {
		bv.ifnull(offset);
	}

	@Override
	public void ifnonnull(int offset) {
		bv.ifnonnull(offset);
	}

	@Override
	public void goto_w(int offset) {
		bv.goto_w(offset);
	}

	@Override
	public void jsr_w(int offset) {
		bv.jsr_w(offset);
	}
}
