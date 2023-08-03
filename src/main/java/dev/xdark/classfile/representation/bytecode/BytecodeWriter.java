package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.BadClassFileFormatException;
import dev.xdark.classfile.bytecode.Bytecodes;
import dev.xdark.classfile.bytecode.PrimitiveConversions;
import dev.xdark.classfile.constantpool.ConstantClass;
import dev.xdark.classfile.constantpool.ConstantDouble;
import dev.xdark.classfile.constantpool.ConstantFieldRef;
import dev.xdark.classfile.constantpool.ConstantFloat;
import dev.xdark.classfile.constantpool.ConstantInt;
import dev.xdark.classfile.constantpool.ConstantInterfaceMethodRef;
import dev.xdark.classfile.constantpool.ConstantLong;
import dev.xdark.classfile.constantpool.ConstantMemberRef;
import dev.xdark.classfile.constantpool.ConstantMethodRef;
import dev.xdark.classfile.constantpool.ConstantNameAndType;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.io.BinaryOutput;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.InvokeDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.representation.impl.LabelImpl;
import dev.xdark.classfile.type.ArrayType;
import dev.xdark.classfile.type.ClassType;
import dev.xdark.classfile.type.InstanceType;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;
import dev.xdark.classfile.type.PrimitiveType;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static dev.xdark.classfile.bytecode.Bytecodes.LOOKUPSWITCH;
import static dev.xdark.classfile.bytecode.Bytecodes.TABLESWITCH;

public final class BytecodeWriter implements BytecodeVisitor, Closeable {
	private final Map<Long, LabelPatch> toPatch = new HashMap<>();
	private final MutableSymbolTable symbolTable;
	private final BinaryOutput output;
	private final dev.xdark.classfile.bytecode.BytecodeVisitor writer;

	public BytecodeWriter(MutableSymbolTable symbolTable, BinaryOutput output) {
		this.symbolTable = symbolTable;
		this.output = output;
		writer = new dev.xdark.classfile.bytecode.BytecodeWriter(output);
	}

	@Override
	public void nop() {
		writer.nop();
	}

	@Override
	public void pushNull() {
		writer.aconst_null();
	}

	@Override
	public void pushInt(int value) {
		ldc(symbolTable.constantPool().add(ConstantInt.create(value)));
	}

	@Override
	public void pushLong(long value) {
		writer.ldc2_w(symbolTable.constantPool().add(ConstantLong.create(value)));

	}

	@Override
	public void pushFloat(float value) {
		ldc(symbolTable.constantPool().add(ConstantFloat.create(value)));
	}

	@Override
	public void pushDouble(double value) {
		writer.ldc2_w(symbolTable.constantPool().add(ConstantDouble.create(value)));
	}

	@Override
	public void pushString(String value) {
		MutableSymbolTable symbolTable = this.symbolTable;
		ldc(symbolTable.addString(value));
	}

	@Override
	public void pushType(ObjectType type) {
		ldc(symbolTable.addType(type));
	}

	@Override
	public void pushType(MethodType type) {
		ldc(symbolTable.addType(type));
	}

	@Override
	public void pushMethodHandle(MethodHandle methodHandle) {
		ldc(symbolTable.addMethodHandle(methodHandle));
	}

	@Override
	public void pushDynamic(ConstantDynamic dynamic) {
		ldc(symbolTable.addConstantDynamic(dynamic));
	}

	@Override
	public void load(ClassType type, int index) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (type instanceof ObjectType) {
			writer.aload(index);
		} else {
			switch (((PrimitiveType) type).kind()) {
				case T_BOOLEAN:
				case T_CHAR:
				case T_BYTE:
				case T_SHORT:
				case T_INT:
					writer.iload(index);
					break;
				case T_FLOAT:
					writer.fload(index);
					break;
				case T_LONG:
					writer.lload(index);
					break;
				case T_DOUBLE:
					writer.dload(index);
					break;
				default:
					throw new IllegalArgumentException(String.format("Bad type %s", type));
			}
		}
	}

	@Override
	public void store(ClassType type, int index) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (type instanceof ObjectType) {
			writer.astore(index);
		} else {
			switch (((PrimitiveType) type).kind()) {
				case T_BOOLEAN:
				case T_CHAR:
				case T_BYTE:
				case T_SHORT:
				case T_INT:
					writer.istore(index);
					break;
				case T_FLOAT:
					writer.fstore(index);
					break;
				case T_LONG:
					writer.lstore(index);
					break;
				case T_DOUBLE:
					writer.dstore(index);
					break;
				default:
					throw new IllegalArgumentException(String.format("Bad type %s", type));
			}
		}
	}

	@Override
	public void arrayLoad(ClassType componentType) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (componentType instanceof ObjectType) {
			writer.aaload();
		} else {
			switch (((PrimitiveType) componentType).kind()) {
				case T_BOOLEAN:
				case T_BYTE:
					writer.baload();
					break;
				case T_CHAR:
					writer.caload();
					break;
				case T_SHORT:
					writer.saload();
					break;
				case T_INT:
					writer.iaload();
					break;
				case T_FLOAT:
					writer.faload();
					break;
				case T_LONG:
					writer.laload();
					break;
				case T_DOUBLE:
					writer.daload();
					break;
				default:
					throw new IllegalArgumentException(String.format("Bad type %s", componentType));
			}
		}
	}

	@Override
	public void arrayStore(ClassType componentType) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (componentType instanceof ObjectType) {
			writer.aastore();
		} else {
			switch (((PrimitiveType) componentType).kind()) {
				case T_BOOLEAN:
				case T_BYTE:
					writer.bastore();
					break;
				case T_CHAR:
					writer.castore();
					break;
				case T_SHORT:
					writer.sastore();
					break;
				case T_INT:
					writer.iastore();
					break;
				case T_FLOAT:
					writer.fastore();
					break;
				case T_LONG:
					writer.lastore();
					break;
				case T_DOUBLE:
					writer.dastore();
					break;
				default:
					throw new IllegalArgumentException(String.format("Bad type %s", componentType));
			}
		}
	}

	@Override
	public void pop() {
		writer.pop();
	}

	@Override
	public void pop2() {
		writer.pop2();
	}

	@Override
	public void dup() {
		writer.dup();
	}

	@Override
	public void dup_x1() {
		writer.dup_x1();
	}

	@Override
	public void dup_x2() {
		writer.dup_x2();
	}

	@Override
	public void dup2() {
		writer.dup2();
	}

	@Override
	public void dup2_x1() {
		writer.dup2_x2();
	}

	@Override
	public void dup2_x2() {
		writer.dup2_x2();
	}

	@Override
	public void swap() {
		writer.swap();
	}

	@Override
	public void mathOp(PrimitiveType type, MathOperation op) {
		int opcode;
		switch (op) {
			case ADD:
				opcode = Bytecodes.IADD;
				break;
			case SUB:
				opcode = Bytecodes.ISUB;
				break;
			case MUL:
				opcode = Bytecodes.IMUL;
				break;
			case DIV:
				opcode = Bytecodes.IDIV;
				break;
			case REM:
				opcode = Bytecodes.IREM;
				break;
			case NEG:
				opcode = Bytecodes.INEG;
				break;
			case SHL:
				opcode = Bytecodes.ISHL;
				break;
			case SHR:
				opcode = Bytecodes.ISHR;
				break;
			case USHR:
				opcode = Bytecodes.IUSHR;
				break;
			case AND:
				opcode = Bytecodes.IAND;
				break;
			case OR:
				opcode = Bytecodes.IOR;
				break;
			case XOR:
				opcode = Bytecodes.IXOR;
				break;
			default:
				throw new IllegalArgumentException(Objects.toString(op));
		}
		int off;
		switch (type.kind()) {
			case T_BOOLEAN:
			case T_CHAR:
			case T_INT:
			case T_BYTE:
			case T_SHORT:
				off = 0;
				break;
			case T_FLOAT:
				off = 2;
				break;
			case T_DOUBLE:
				off = 3;
				break;
			case T_LONG:
				off = 1;
				break;
			default:
				throw new IllegalArgumentException(type.descriptor());
		}
		int offset = opcode + off;
		try {
			output.writeByte(offset);
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public void increment(int variableIndex, int value) {
		writer.iinc(variableIndex, value);
	}

	@Override
	public void primitiveConversion(PrimitiveType a, PrimitiveType b) {
		PrimitiveConversions.performConversion(writer, a, b);
	}

	@Override
	public void lcmp() {
		writer.lcmp();
	}

	@Override
	public void fcmpl() {
		writer.fcmpl();
	}

	@Override
	public void fcmpg() {
		writer.fcmpg();
	}

	@Override
	public void dcmpl() {
		writer.dcmpl();
	}

	@Override
	public void dcmpg() {
		writer.dcmpg();
	}

	@Override
	public void label(Label label) {
		label.setPosition((int) output.position());
	}

	@Override
	public void unaryBranch(UnaryCondition condition, Label label) {
		LabelImpl impl = (LabelImpl) label;
		int offset;
		long pos = output.position();
		if (impl.isResolved()) {
			offset = impl.getOffset(pos);
		} else {
			toPatch.put(pos + 1L, new LabelPatch(label, pos, false));
			offset = -1;
		}
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		switch (condition) {
			case EQ:
				writer.ifeq(offset);
				break;
			case NE:
				writer.ifne(offset);
				break;
			case LT:
				writer.iflt(offset);
				break;
			case GE:
				writer.ifge(offset);
				break;
			case GT:
				writer.ifgt(offset);
				break;
			case LE:
				writer.ifle(offset);
				break;
			case NULL:
				writer.ifnull(offset);
				break;
			case NONNULL:
				writer.ifnonnull(offset);
				break;
		}
	}

	@Override
	public void binaryBranch(BinaryCondition condition, Label label) {
		LabelImpl impl = (LabelImpl) label;
		int offset;
		long pos = output.position();
		if (impl.isResolved()) {
			offset = impl.getOffset(pos);
		} else {
			toPatch.put(pos + 1L, new LabelPatch(label, pos, false));
			offset = -1;
		}
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		switch (condition) {
			case EQ:
				writer.if_icmpeq(offset);
				break;
			case NE:
				writer.if_icmpne(offset);
				break;
			case LT:
				writer.if_icmplt(offset);
				break;
			case GE:
				writer.if_icmpge(offset);
				break;
			case GT:
				writer.if_icmpgt(offset);
				break;
			case LE:
				writer.if_icmple(offset);
				break;
			case AEQ:
				writer.if_acmpeq(offset);
				break;
			case ANE:
				writer.if_acmpne(offset);
				break;
		}
	}

	@Override
	public void goto_(Label label) {
		LabelImpl impl = (LabelImpl) label;
		long pos = output.position();
		if (impl.isResolved()) {
			writer.goto_w(impl.getOffset(pos));
		} else {
			toPatch.put(pos + 1L, new LabelPatch(label, pos, true));
			writer.goto_w(-1);
		}
	}

	@Override
	public void jsr(Label label) {
		LabelImpl impl = (LabelImpl) label;
		long pos = output.position();
		if (impl.isResolved()) {
			writer.jsr_w(impl.getOffset(pos));
		} else {
			toPatch.put(pos, new LabelPatch(label, pos + 1L, true));
			writer.jsr_w(-1);
		}
	}

	@Override
	public void ret(int index) {
		writer.ret(index);
	}

	@Override
	public void tableSwitch(Label defaultBranch, int low, int high, List<Label> cases) {
		long src = output.position();
		try {
			BinaryOutput out = output;
			out.writeByte(TABLESWITCH);
			long pos = out.position();
			pos = pos + (4L - pos & 3L);
			out.position(pos);
			out.writeInt(getOrPatchSwitch(src, out.position(), defaultBranch));
			out.writeInt(low);
			out.writeInt(high);
			for (Label l : cases) {
				out.writeInt(getOrPatchSwitch(src, out.position(), l));
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public void lookupSwitch(Label defaultBranch, int[] keys, List<Label> cases) {
		long src = output.position();
		try {
			BinaryOutput out = output;
			out.writeByte(LOOKUPSWITCH);
			long pos = out.position();
			pos = pos + (4L - pos & 3L);
			out.position(pos);
			out.writeInt(getOrPatchSwitch(src, out.position(), defaultBranch));
			out.writeInt(keys.length);
			for (int i = 0; i < keys.length; i++) {
				out.writeInt(keys[i]);
				out.writeInt(getOrPatchSwitch(src, out.position(), cases.get(i)));
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}

	}

	@Override
	public void return_(ClassType type) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (type instanceof ObjectType) {
			writer.areturn();
		} else {
			switch (((PrimitiveType) type).kind()) {
				case T_BOOLEAN:
				case T_CHAR:
				case T_BYTE:
				case T_SHORT:
				case T_INT:
					writer.ireturn();
					break;
				case T_FLOAT:
					writer.freturn();
					break;
				case T_LONG:
					writer.lreturn();
					break;
				case T_DOUBLE:
					writer.dreturn();
					break;
				case T_VOID:
					writer.return_();
					break;
				default:
					throw new IllegalArgumentException(String.format("Bad type %s", type));
			}
		}
	}

	@Override
	public void getStatic(InstanceType owner, String name, ClassType type) {
		putMemberInfo(Bytecodes.GETSTATIC, owner.internalName(), name, type.descriptor(), ConstantFieldRef::create);
	}

	@Override
	public void putStatic(InstanceType owner, String name, ClassType type) {
		putMemberInfo(Bytecodes.PUTSTATIC, owner.internalName(), name, type.descriptor(), ConstantFieldRef::create);
	}

	@Override
	public void getField(InstanceType owner, String name, ClassType type) {
		putMemberInfo(Bytecodes.GETFIELD, owner.internalName(), name, type.descriptor(), ConstantFieldRef::create);
	}

	@Override
	public void putField(InstanceType owner, String name, ClassType type) {
		putMemberInfo(Bytecodes.PUTFIELD, owner.internalName(), name, type.descriptor(), ConstantFieldRef::create);
	}

	@Override
	public void invokeVirtual(ObjectType owner, String name, MethodType type) {
		putMemberInfo(Bytecodes.INVOKEVIRTUAL, owner.internalName(), name, type.descriptor(), ConstantMethodRef::create);
	}

	@Override
	public void invokeSpecial(ObjectType owner, String name, MethodType type, boolean itf) {
		putMemberInfo(Bytecodes.INVOKESPECIAL, owner.internalName(), name, type.descriptor(), itf ? ConstantInterfaceMethodRef::create : ConstantMethodRef::create);
	}

	@Override
	public void invokeStatic(ObjectType owner, String name, MethodType type, boolean itf) {
		putMemberInfo(Bytecodes.INVOKESTATIC, owner.internalName(), name, type.descriptor(), itf ? ConstantInterfaceMethodRef::create : ConstantMethodRef::create);
	}

	@Override
	public void invokeInterface(ObjectType owner, String name, MethodType type) {
		putMemberInfo(Bytecodes.INVOKEINTERFACE, owner.internalName(), name, type.descriptor(), ConstantInterfaceMethodRef::create);
		try {
			output.writeByte(type.parameterTypes().stream().mapToInt(x -> x == PrimitiveType.T_LONG || x == PrimitiveType.T_DOUBLE ? 2 : 1).sum() + 1);
			output.writeByte(0);
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	@Override
	public void invokeDynamic(InvokeDynamic invokeDynamic) {
		writer.invoke_dynamic(symbolTable.addInvokeDynamic(invokeDynamic));
	}

	@Override
	public void allocate(ObjectType type) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (type instanceof InstanceType) {
			writer.new_(addClass(type));
		} else {
			ArrayType arrayType = (ArrayType) type;
			ClassType component = arrayType.component();
			int dims = arrayType.dimensions();
			if (dims == 1) {
				if (component instanceof PrimitiveType) {
					writer.new_array(((PrimitiveType) component).kind().id());
				} else {
					writer.anew_array(addClass((ObjectType) component));
				}
			} else {
				writer.multi_anew_array(addClass(arrayType), dims);
			}
		}
	}

	@Override
	public void arrayLength() {
		writer.array_length();
	}

	@Override
	public void athrow() {
		writer.athrow();
	}

	@Override
	public void checkCast(ObjectType type) {
		writer.check_cast(symbolTable.addType(type));
	}

	@Override
	public void instanceOf(ObjectType type) {
		writer.instanceof_(symbolTable.addType(type));
	}

	@Override
	public void monitorEnter() {
		writer.monitor_enter();
	}

	@Override
	public void monitorExit() {
		writer.monitor_exit();
	}

	@Override
	public void close() throws IOException {
		BinaryOutput output = this.output;
		long position = output.position();
		for (Map.Entry<Long, LabelPatch> entry : toPatch.entrySet()) {
			LabelPatch patch = entry.getValue();
			LabelImpl label = (LabelImpl) patch.label;
			if (!label.isResolved()) {
				throw new BadClassFileFormatException("Unresolved label " + label);
			}
			long jumpAt = entry.getKey();
			long origin = patch.origin;
			int labelAt = label.getPosition();
			output.position(jumpAt);
			long bytecodePos = origin;
			if (bytecodePos == -1L) {
				bytecodePos = jumpAt;
			}
			int off = labelAt - ((int) bytecodePos);
			if (patch.wide) {
				output.writeInt(off);
			} else {
				output.writeShort(off);
			}
		}
		output.position(position);
	}

	private void ldc(int idx) {
		dev.xdark.classfile.bytecode.BytecodeVisitor writer = this.writer;
		if (idx > 255) {
			writer.ldc_w(idx);
		} else {
			writer.ldc(idx);
		}
	}

	private int addClass(String name) {
		MutableConstantPool constantPool = this.symbolTable.constantPool();
		return constantPool.add(ConstantClass.create(constantPool.add(ConstantUtf8.create(name))));
	}

	private int addClass(ObjectType type) {
		return symbolTable.addType(type);
	}

	private void putMemberInfo(int opcode, String owner, String name, String descriptor, MemberRefCreator creator) {
		MutableSymbolTable symbolTable = this.symbolTable;
		MutableConstantPool constantPool = symbolTable.constantPool();
		BinaryOutput output = this.output;
		try {
			int classIdx = addClass(owner);
			int nameIdx = constantPool.add(ConstantUtf8.create(name));
			int descriptorIdx = constantPool.add(ConstantUtf8.create(descriptor));
			int nameAndTypeIdx = constantPool.add(ConstantNameAndType.create(nameIdx, descriptorIdx));
			int memberRefIdx = constantPool.add(creator.create(classIdx, nameAndTypeIdx));
			output.writeByte(opcode);
			output.writeShort(memberRefIdx);
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}

	private int getOrPatchSwitch(long src, long pos, Label l) {
		LabelImpl impl = (LabelImpl) l;
		if (impl.isResolved()) {
			return impl.getOffset(src);
		}
		toPatch.put(pos, new LabelPatch(l, src, true));
		return -1;
	}

	private interface MemberRefCreator {

		ConstantMemberRef create(int classIdx, int nameAndTypeIdx);
	}

	private static final class LabelPatch {
		final Label label;
		final long origin;
		final boolean wide;

		private LabelPatch(Label label, long origin, boolean wide) {
			this.label = label;
			this.origin = origin;
			this.wide = wide;
		}
	}
}
