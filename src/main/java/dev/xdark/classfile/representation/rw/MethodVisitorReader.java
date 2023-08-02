package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.attribute.code.ExceptionTableEntry;
import dev.xdark.classfile.bytecode.BytecodePump;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.representation.bytecode.BytecodeReader;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.FixedLabelArray;
import dev.xdark.classfile.representation.bytecode.InstructionPositionTracker;
import dev.xdark.classfile.representation.bytecode.LabelArray;
import dev.xdark.classfile.representation.bytecode.LabelMarkVisitor;
import dev.xdark.classfile.representation.bytecode.LabelPlacementTracker;
import dev.xdark.classfile.representation.bytecode.SimpleInstructionPositionTracker;
import dev.xdark.classfile.representation.bytecode.SimpleLabelPlacementTracker;
import dev.xdark.classfile.type.InstanceType;

import java.io.IOException;
import java.io.UncheckedIOException;

final class MethodVisitorReader extends MemberVisitorReader implements dev.xdark.classfile.MethodVisitor {

	MethodVisitorReader(MethodVisitor mv, SymbolTable symbolTable) {
		super(mv, symbolTable);
	}

	@Override
	public void visit(int nameIndex, SpecAttribute attribute) {
		if (attribute instanceof CodeAttribute) {
			CodeVisitor cv = ((MethodVisitor) mv).visitCode();
			if (cv == null) {
				return;
			}
			CodeAttribute code = (CodeAttribute) attribute;
			cv.visitMaxs(code.maxStack(), code.maxLocals());
			BinaryInput bytecode = code.bytecode();
			long readable = bytecode.readableBytes();
			if (readable > 65535) {
				throw new IllegalStateException(String.format("Method too large %d", readable));
			}
			LabelArray array = new FixedLabelArray((int) readable);
			{
				InstructionPositionTracker tracker = new SimpleInstructionPositionTracker();
				// Mark all labels beforehand
				try {
					BytecodePump pump = new BytecodePump(bytecode.duplicate(), new LabelMarkVisitor(tracker, array), tracker);
					pump.pumpAll();
				} catch (IOException ex) {
					throw new UncheckedIOException(ex);
				}
			}
			ConstantPool constantPool = symbolTable.constantPool();
			// Create all try/catch blocks
			for (ExceptionTableEntry entry : code.exceptionTable()) {
				array.create(entry.start());
				array.create(entry.end());
				array.create(entry.handler());
			}
			// Visit all instructions
			BytecodeVisitor bv = cv.visitBytecode();
			if (bv != null) {
				try {
					LabelPlacementTracker tracker = new SimpleLabelPlacementTracker(array, bv);
					BytecodeReader reader = new BytecodeReader(symbolTable, bv, array, tracker);
					BytecodePump pump = new BytecodePump(bytecode, reader, tracker);
					pump.pumpAll();
				} catch (IOException ex) {
					throw new UncheckedIOException(ex);
				}
			}
			// Visit all try/catch blocks
			for (ExceptionTableEntry entry : code.exceptionTable()) {
				int type = entry.typeIndex();
				cv.visitTryCatchBlock(TryCatchBlock.create(
						array.create(entry.start()),
						array.create(entry.end()),
						array.create(entry.handler()),
						type == 0 ? null : InstanceType.ofInternalName(
								constantPool.get(constantPool.get(type, Tag.Class).nameIndex(), Tag.Utf8).value()
						)
				));
			}
			return;
		}
		super.visit(nameIndex, attribute);
	}
}
