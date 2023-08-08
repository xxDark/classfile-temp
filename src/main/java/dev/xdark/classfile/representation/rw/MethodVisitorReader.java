package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.Attribute;
import dev.xdark.classfile.attribute.IndexedAttribute;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.attribute.code.ExceptionTableEntry;
import dev.xdark.classfile.attribute.code.LineNumber;
import dev.xdark.classfile.attribute.code.LineNumberTableAttribute;
import dev.xdark.classfile.attribute.method.AnnotationDefaultAttribute;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementInternal;
import dev.xdark.classfile.bytecode.BytecodePump;
import dev.xdark.classfile.constantpool.ConstantPool;
import dev.xdark.classfile.constantpool.Tag;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.MethodVisitor;
import dev.xdark.classfile.representation.SymbolTable;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
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
			LabelArray labelArray = new FixedLabelArray((int) readable);
			{
				InstructionPositionTracker tracker = new SimpleInstructionPositionTracker();
				// Mark all labels beforehand
				BytecodePump pump = new BytecodePump(bytecode.duplicate(), new LabelMarkVisitor(tracker, labelArray), tracker);
				pump.pumpAll();
			}
			ConstantPool constantPool = symbolTable.constantPool();
			// Create all try/catch blocks
			for (ExceptionTableEntry entry : code.exceptionTable()) {
				labelArray.create(entry.start());
				labelArray.create(entry.end());
				labelArray.create(entry.handler());
			}
			// Visit all nested attributes
			for (IndexedAttribute indexedAttr : code.attributes()) {
				Attribute attr = indexedAttr.getAttribute();
				if (attr instanceof LineNumberTableAttribute) {
					// Set line numbers
					LineNumberTableAttribute lineNumberTable = (LineNumberTableAttribute) attr;
					for (LineNumber lineNumber : lineNumberTable.lineNumbers()) {
						Label l = labelArray.create(lineNumber.start());
						l.setLineNumber(lineNumber.lineNumber());
					}
					break;
				}
			}
			// Visit all instructions
			BytecodeVisitor bv = cv.visitBytecode();
			if (bv != null) {
				LabelPlacementTracker tracker = new SimpleLabelPlacementTracker(labelArray, bv);
				BytecodeReader reader = new BytecodeReader(symbolTable, bv, labelArray, tracker);
				BytecodePump pump = new BytecodePump(bytecode, reader, tracker);
				pump.pumpAll();
			}
			// Visit all try/catch blocks
			for (ExceptionTableEntry entry : code.exceptionTable()) {
				int type = entry.typeIndex();
				cv.visitTryCatchBlock(TryCatchBlock.create(
						labelArray.create(entry.start()),
						labelArray.create(entry.end()),
						labelArray.create(entry.handler()),
						type == 0 ? null : InstanceType.ofInternalName(
								constantPool.get(constantPool.get(type, Tag.Class).nameIndex(), Tag.Utf8).value()
						)
				));
			}
			return;
		}
		if (attribute instanceof AnnotationDefaultAttribute) {
			AnnotationValueSink sink = ((MethodVisitor) mv).annotationDefaultSink();
			if (sink != null) {
				AnnotationValue value = ((ElementInternal) ((AnnotationDefaultAttribute) attribute).defaultValue()).normalise(symbolTable.constantPool());
				value.accept(sink);
			}
			return;
		}
		super.visit(nameIndex, attribute);
	}
}
