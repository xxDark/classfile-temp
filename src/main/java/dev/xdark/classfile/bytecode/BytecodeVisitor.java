package dev.xdark.classfile.bytecode;

import dev.xdark.classfile.io.BinaryInput;

public interface BytecodeVisitor extends PrimitiveConversion {

	void nop();

	void aconst_null();

	void iconst(int n);

	void lconst_0();

	void lconst_1();

	void fconst_0();

	void fconst_1();

	void fconst_2();

	void dconst_0();

	void dconst_1();

	void bipush(int value);

	void sipush(int value);

	void ldc(int index);

	void ldc_w(int index);

	void ldc2_w(int index);

	void iload(int index);

	void lload(int index);

	void fload(int index);

	void dload(int index);

	void aload(int index);

	void iload_0();

	void iload_1();

	void iload_2();

	void iload_3();

	void lload_0();

	void lload_1();

	void lload_2();

	void lload_3();

	void fload_0();

	void fload_1();

	void fload_2();

	void fload_3();

	void dload_0();

	void dload_1();

	void dload_2();

	void dload_3();

	void aload_0();

	void aload_1();

	void aload_2();

	void aload_3();

	void iaload();

	void laload();

	void faload();

	void daload();

	void aaload();

	void baload();

	void caload();

	void saload();

	void istore(int index);

	void lstore(int index);

	void fstore(int index);

	void dstore(int index);

	void astore(int index);

	void istore_0();

	void istore_1();

	void istore_2();

	void istore_3();

	void lstore_0();

	void lstore_1();

	void lstore_2();

	void lstore_3();

	void fstore_0();

	void fstore_1();

	void fstore_2();

	void fstore_3();

	void dstore_0();

	void dstore_1();

	void dstore_2();

	void dstore_3();

	void astore_0();

	void astore_1();

	void astore_2();

	void astore_3();

	void iastore();

	void lastore();

	void fastore();

	void dastore();

	void aastore();

	void bastore();

	void castore();

	void sastore();

	void pop();

	void pop2();

	void dup();

	void dup_x1();

	void dup_x2();

	void dup2();

	void dup2_x1();

	void dup2_x2();

	void swap();

	void iadd();

	void ladd();

	void fadd();

	void dadd();

	void isub();

	void lsub();

	void fsub();

	void dsub();

	void imul();

	void lmul();

	void fmul();

	void dmul();

	void idiv();

	void ldiv();

	void fdiv();

	void ddiv();

	void irem();

	void lrem();

	void frem();

	void drem();

	void ineg();

	void lneg();

	void fneg();

	void dneg();

	void ishl();

	void lshl();

	void ishr();

	void lshr();

	void iushr();

	void lushr();

	void iand();

	void land();

	void ior();

	void lor();

	void ixor();

	void lxor();

	void iinc(int var, int value);

	void lcmp();

	void fcmpl();

	void fcmpg();

	void dcmpl();

	void dcmpg();

	void ifeq(int offset);

	void ifne(int offset);

	void iflt(int offset);

	void ifge(int offset);

	void ifgt(int offset);

	void ifle(int offset);

	void if_icmpeq(int offset);

	void if_icmpne(int offset);

	void if_icmplt(int offset);

	void if_icmpge(int offset);

	void if_icmpgt(int offset);

	void if_icmple(int offset);

	void if_acmpeq(int offset);

	void if_acmpne(int offset);

	void goto_(int offset);

	void jsr(int offset);

	void ret(int index);

	void table_switch(int defaultBranchOffset, int low, int high, BinaryInput offsets);

	void lookup_switch(int defaultBranchOffset, BinaryInput pairs);

	void ireturn();

	void lreturn();

	void freturn();

	void dreturn();

	void areturn();

	void return_();

	void get_static(int fieldIndex);

	void put_static(int fieldIndex);

	void get_field(int fieldIndex);

	void put_field(int fieldIndex);

	void invoke_virtual(int methodIndex);

	void invoke_special(int methodIndex);

	void invoke_static(int methodIndex);

	void invoke_interface(int methodIndex, int count);

	void invoke_dynamic(int invokeDynamicIndex);

	void new_(int classIndex);

	void new_array(int type);

	void anew_array(int classIndex);

	void array_length();

	void athrow();

	void check_cast(int classIndex);

	void instanceof_(int classIndex);

	void monitor_enter();

	void monitor_exit();

	void wide(int opcode, int op1, int op2);

	void multi_anew_array(int classIndex, int dimensions);

	void ifnull(int offset);

	void ifnonnull(int offset);

	void goto_w(int offset);

	void jsr_w(int offset);
}
