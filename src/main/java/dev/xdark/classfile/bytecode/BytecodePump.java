package dev.xdark.classfile.bytecode;

import dev.xdark.classfile.io.BinaryInput;

import java.io.IOException;
import java.util.function.LongConsumer;

import static dev.xdark.classfile.bytecode.Bytecodes.*;

public final class BytecodePump {
	private final BinaryInput input;
	private final BytecodeVisitor visitor;
	private final LongConsumer pcConsumer;
	private final long start;

	public BytecodePump(BinaryInput input, BytecodeVisitor visitor, LongConsumer pcConsumer) {
		this.input = input;
		this.visitor = visitor;
		this.pcConsumer = pcConsumer;
		start = input.position();
	}

	public BytecodePump(BinaryInput input, BytecodeVisitor visitor) {
		this(input, visitor, null);
	}

	public void pumpAll() {
		while (pump()) ;
	}

	public boolean pump() {
		BinaryInput input = this.input;
		if (input.isReadable(1L)) {
			BytecodeVisitor v = visitor;
			LongConsumer pcConsumer = this.pcConsumer;
			if (pcConsumer != null) {
				pcConsumer.accept(input.position() - start);
			}
			int opcode = input.readUnsignedByte();
			switch (opcode) {
				case NOP:
					v.nop();
					break;
				case ACONST_NULL:
					v.aconst_null();
					break;
				case ICONST_M1:
				case ICONST_0:
				case ICONST_1:
				case ICONST_2:
				case ICONST_3:
				case ICONST_4:
				case ICONST_5:
					v.iconst(opcode - ICONST_0);
					break;
				case LCONST_0:
					v.lconst_0();
					break;
				case LCONST_1:
					v.lconst_1();
					break;
				case FCONST_0:
					v.fconst_0();
					break;
				case FCONST_1:
					v.fconst_1();
					break;
				case FCONST_2:
					v.fconst_2();
					break;
				case DCONST_0:
					v.dconst_0();
					break;
				case DCONST_1:
					v.dconst_1();
					break;
				case BIPUSH:
					v.bipush(input.readByte());
					break;
				case SIPUSH:
					v.sipush(input.readShort());
					break;
				case LDC:
					v.ldc(input.readUnsignedByte());
					break;
				case LDC_W:
					v.ldc_w(input.readUnsignedShort());
					break;
				case LDC2_W:
					v.ldc2_w(input.readUnsignedShort());
					break;
				case ILOAD:
					v.iload(input.readUnsignedByte());
					break;
				case LLOAD:
					v.lload(input.readUnsignedByte());
					break;
				case FLOAD:
					v.fload(input.readUnsignedByte());
					break;
				case DLOAD:
					v.dload(input.readUnsignedByte());
					break;
				case ALOAD:
					v.aload(input.readUnsignedByte());
					break;

				case ILOAD_0:
					v.iload_0();
					break;
				case ILOAD_1:
					v.iload_1();
					break;
				case ILOAD_2:
					v.iload_2();
					break;
				case ILOAD_3:
					v.iload_3();
					break;
				case LLOAD_0:
					v.lload_0();
					break;
				case LLOAD_1:
					v.lload_1();
					break;
				case LLOAD_2:
					v.lload_2();
					break;
				case LLOAD_3:
					v.lload_3();
					break;

				case FLOAD_0:
					v.fload_0();
					break;
				case FLOAD_1:
					v.fload_1();
					break;
				case FLOAD_2:
					v.fload_2();
					break;
				case FLOAD_3:
					v.fload_3();
					break;

				case DLOAD_0:
					v.dload_0();
					break;
				case DLOAD_1:
					v.dload_1();
					break;
				case DLOAD_2:
					v.dload_2();
					break;
				case DLOAD_3:
					v.dload_3();
					break;

				case ALOAD_0:
					v.aload_0();
					break;
				case ALOAD_1:
					v.aload_1();
					break;
				case ALOAD_2:
					v.aload_2();
					break;
				case ALOAD_3:
					v.aload_3();
					break;

				case IALOAD:
					v.iaload();
					break;
				case LALOAD:
					v.laload();
					break;
				case FALOAD:
					v.faload();
					break;
				case DALOAD:
					v.daload();
					break;
				case AALOAD:
					v.aaload();
					break;
				case BALOAD:
					v.baload();
					break;
				case CALOAD:
					v.caload();
					break;
				case SALOAD:
					v.saload();
					break;

				case ISTORE:
					v.istore(input.readUnsignedByte());
					break;
				case LSTORE:
					v.lstore(input.readUnsignedByte());
					break;
				case FSTORE:
					v.fstore(input.readUnsignedByte());
					break;
				case DSTORE:
					v.dstore(input.readUnsignedByte());
					break;
				case ASTORE:
					v.astore(input.readUnsignedByte());
					break;

				case ISTORE_0:
					v.istore_0();
					break;
				case ISTORE_1:
					v.istore_1();
					break;
				case ISTORE_2:
					v.istore_2();
					break;
				case ISTORE_3:
					v.istore_3();
					break;
				case LSTORE_0:
					v.lstore_0();
					break;
				case LSTORE_1:
					v.lstore_1();
					break;
				case LSTORE_2:
					v.lstore_2();
					break;
				case LSTORE_3:
					v.lstore_3();
					break;

				case FSTORE_0:
					v.fstore_0();
					break;
				case FSTORE_1:
					v.fstore_1();
					break;
				case FSTORE_2:
					v.fstore_2();
					break;
				case FSTORE_3:
					v.fstore_3();
					break;

				case DSTORE_0:
					v.dstore_0();
					break;
				case DSTORE_1:
					v.dstore_1();
					break;
				case DSTORE_2:
					v.dstore_2();
					break;
				case DSTORE_3:
					v.dstore_3();
					break;

				case ASTORE_0:
					v.astore_0();
					break;
				case ASTORE_1:
					v.astore_1();
					break;
				case ASTORE_2:
					v.astore_2();
					break;
				case ASTORE_3:
					v.astore_3();
					break;

				case IASTORE:
					v.iastore();
					break;
				case LASTORE:
					v.lastore();
					break;
				case FASTORE:
					v.fastore();
					break;
				case DASTORE:
					v.dastore();
					break;
				case AASTORE:
					v.aastore();
					break;
				case BASTORE:
					v.bastore();
					break;
				case CASTORE:
					v.castore();
					break;
				case SASTORE:
					v.sastore();
					break;

				case POP:
					v.pop();
					break;
				case POP2:
					v.pop2();
					break;
				case DUP:
					v.dup();
					break;
				case DUP_X1:
					v.dup_x1();
					break;
				case DUP_X2:
					v.dup_x2();
					break;
				case DUP2:
					v.dup2();
					break;
				case DUP2_X1:
					v.dup2_x1();
					break;
				case DUP2_X2:
					v.dup2_x2();
					break;
				case SWAP:
					v.swap();
					break;

				case IADD:
					v.iadd();
					break;
				case LADD:
					v.ladd();
					break;
				case FADD:
					v.fadd();
					break;
				case DADD:
					v.dadd();
					break;

				case ISUB:
					v.isub();
					break;
				case LSUB:
					v.lsub();
					break;
				case FSUB:
					v.fsub();
					break;
				case DSUB:
					v.dsub();
					break;

				case IMUL:
					v.imul();
					break;
				case LMUL:
					v.lmul();
					break;
				case FMUL:
					v.fmul();
					break;
				case DMUL:
					v.dmul();
					break;

				case IDIV:
					v.idiv();
					break;
				case LDIV:
					v.ldiv();
					break;
				case FDIV:
					v.fdiv();
					break;
				case DDIV:
					v.ddiv();
					break;

				case IREM:
					v.irem();
					break;
				case LREM:
					v.lrem();
					break;
				case FREM:
					v.frem();
					break;
				case DREM:
					v.drem();
					break;

				case INEG:
					v.ineg();
					break;
				case LNEG:
					v.lneg();
					break;
				case FNEG:
					v.fneg();
					break;
				case DNEG:
					v.dneg();
					break;

				case ISHL:
					v.ishl();
					break;
				case LSHL:
					v.lshl();
					break;
				case ISHR:
					v.ishr();
					break;
				case LSHR:
					v.lshr();
					break;
				case IUSHR:
					v.iushr();
					break;
				case LUSHR:
					v.lushr();
					break;
				case IAND:
					v.iand();
					break;
				case LAND:
					v.land();
					break;
				case IOR:
					v.ior();
					break;
				case LOR:
					v.lor();
					break;
				case IXOR:
					v.ixor();
					break;
				case LXOR:
					v.lxor();
					break;
				case IINC:
					v.iinc(input.readUnsignedByte(), input.readByte());
					break;
				case I2L:
					v.i2l();
					break;
				case I2F:
					v.i2f();
					break;
				case I2D:
					v.i2d();
					break;
				case L2I:
					v.l2i();
					break;
				case L2F:
					v.l2f();
					break;
				case L2D:
					v.l2d();
					break;
				case F2I:
					v.f2i();
					break;
				case F2L:
					v.f2l();
					break;
				case F2D:
					v.f2d();
					break;
				case D2I:
					v.d2i();
					break;
				case D2L:
					v.d2l();
					break;
				case D2F:
					v.d2f();
					break;
				case I2B:
					v.i2b();
					break;
				case I2C:
					v.i2c();
					break;
				case I2S:
					v.i2s();
					break;
				case LCMP:
					v.lcmp();
					break;
				case FCMPL:
					v.fcmpl();
					break;
				case FCMPG:
					v.fcmpg();
					break;
				case DCMPL:
					v.dcmpl();
					break;
				case DCMPG:
					v.dcmpg();
					break;

				case IFEQ:
					v.ifeq(input.readShort());
					break;
				case IFNE:
					v.ifne(input.readShort());
					break;
				case IFLT:
					v.iflt(input.readShort());
					break;
				case IFGE:
					v.ifge(input.readShort());
					break;
				case IFGT:
					v.ifgt(input.readShort());
					break;
				case IFLE:
					v.ifle(input.readShort());
					break;
				case IF_ICMPEQ:
					v.if_icmpeq(input.readShort());
					break;
				case IF_ICMPNE:
					v.if_icmpne(input.readShort());
					break;
				case IF_ICMPLT:
					v.if_icmplt(input.readShort());
					break;
				case IF_ICMPGE:
					v.if_icmpge(input.readShort());
					break;
				case IF_ICMPGT:
					v.if_icmpgt(input.readShort());
					break;
				case IF_ICMPLE:
					v.if_icmple(input.readShort());
					break;
				case IF_ACMPEQ:
					v.if_acmpeq(input.readShort());
					break;
				case IF_ACMPNE:
					v.if_acmpne(input.readShort());
					break;
				case GOTO:
					v.goto_(input.readShort());
					break;
				case JSR:
					v.jsr(input.readShort());
					break;
				case RET:
					v.ret(input.readUnsignedByte());
					break;
				case TABLESWITCH: {
					long pos = input.position();
					pos += (4 - (pos - start) & 3);
					input.position(pos);
					int defaultBranchOffset = input.readInt();
					int low = input.readInt();
					int high = input.readInt();
					long limit = (high - low + 1) * 4L;
					input.skipBytes(limit);
					BinaryInput slice = input.fork(pos + 12L);
					slice.limit(limit);
					v.table_switch(defaultBranchOffset, low, high, slice);
					break;
				}
				case LOOKUPSWITCH: {
					long pos = input.position();
					pos += (4 - (pos - start) & 3);
					input.position(pos);
					int defaultBranchOffset = input.readInt();
					long npairs = input.readUnsignedInt();
					long limit = npairs * 8L;
					if (limit < 0L) {
						throw new IllegalStateException("Too many lookupswitch cases " + npairs);
					}
					input.skipBytes(limit);
					BinaryInput slice = input.fork(pos + 8L);
					slice.limit(limit);
					v.lookup_switch(defaultBranchOffset, slice);
					break;
				}
				case IRETURN:
					v.ireturn();
					break;
				case LRETURN:
					v.lreturn();
					break;
				case FRETURN:
					v.freturn();
					break;
				case DRETURN:
					v.dreturn();
					break;
				case ARETURN:
					v.areturn();
					break;
				case RETURN:
					v.return_();
					break;
				case GETSTATIC:
					v.get_static(input.readUnsignedShort());
					break;
				case PUTSTATIC:
					v.put_static(input.readUnsignedShort());
					break;
				case GETFIELD:
					v.get_field(input.readUnsignedShort());
					break;
				case PUTFIELD:
					v.put_field(input.readUnsignedShort());
					break;
				case INVOKEVIRTUAL:
					v.invoke_virtual(input.readUnsignedShort());
					break;
				case INVOKESPECIAL:
					v.invoke_special(input.readUnsignedShort());
					break;
				case INVOKESTATIC:
					v.invoke_static(input.readUnsignedShort());
					break;
				case INVOKEINTERFACE: {
					v.invoke_interface(input.readUnsignedShort(), input.readUnsignedByte());
					input.skipBytes(1L);
					break;
				}
				case INVOKEDYNAMIC: {
					v.invoke_dynamic(input.readUnsignedShort());
					input.skipBytes(2L);
					break;
				}
				case NEW:
					v.new_(input.readUnsignedShort());
					break;
				case NEWARRAY:
					v.new_array(input.readUnsignedByte());
					break;
				case ANEWARRAY:
					v.anew_array(input.readUnsignedShort());
					break;
				case ARRAYLENGTH:
					v.array_length();
					break;
				case ATHROW:
					v.athrow();
					break;
				case CHECKCAST:
					v.check_cast(input.readUnsignedShort());
					break;
				case INSTANCEOF:
					v.instanceof_(input.readUnsignedShort());
					break;
				case MONITORENTER:
					v.monitor_enter();
					break;
				case MONITOREXIT:
					v.monitor_exit();
					break;
				case WIDE: {
					int wideOpcode = input.readUnsignedByte();
					if (wideOpcode == IINC) {
						v.wide(IINC, input.readUnsignedShort(), input.readShort());
					} else {
						v.wide(wideOpcode, input.readUnsignedShort(), -1);
					}
					break;
				}
				case MULTIANEWARRAY:
					v.multi_anew_array(input.readUnsignedShort(), input.readUnsignedByte());
					break;
				case IFNULL:
					v.ifnull(input.readShort());
					break;
				case IFNONNULL:
					v.ifnonnull(input.readShort());
					break;
				case GOTO_W:
					v.goto_w(input.readInt());
					break;
				case JSR_W:
					v.jsr_w(input.readInt());
					break;
				default:
					throw new IllegalStateException(String.format("Unknown opcode %d", opcode));
			}
			return true;
		}
		return false;
	}
}
