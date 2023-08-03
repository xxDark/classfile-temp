package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.IndexedAttribute;
import dev.xdark.classfile.attribute.UnknownAttribute;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.attribute.code.ExceptionTableEntry;
import dev.xdark.classfile.constantpool.ConstantClass;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.io.BufferInput;
import dev.xdark.classfile.io.ByteArrayOutput;
import dev.xdark.classfile.representation.AttributesVisitor;
import dev.xdark.classfile.representation.CodeVisitor;
import dev.xdark.classfile.representation.MutableSymbolTable;
import dev.xdark.classfile.representation.TryCatchBlock;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.bytecode.BytecodeWriter;
import dev.xdark.classfile.type.InstanceType;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

final class CodeVisitorWriter implements CodeVisitor {
	private final List<TryCatchBlock> tryCatchBlocks = new ArrayList<>();
	private final List<IndexedAttribute> attributes = new ArrayList<>();
	private final MutableSymbolTable symtab;
	private int maxStack, maxLocals;
	private ByteArrayOutput bytecodeOutput;
	private BytecodeWriter bytecodeWriter;

	CodeVisitorWriter(MutableSymbolTable symtab) {
		this.symtab = symtab;
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
	}

	@Override
	public void visitTryCatchBlock(TryCatchBlock tryCatchBlock) {
		tryCatchBlocks.add(tryCatchBlock);
	}

	@Override
	public BytecodeVisitor visitBytecode() {
		BytecodeWriter writer = bytecodeWriter;
		if (writer == null) {
			ByteArrayOutput bc = new ByteArrayOutput();
			bytecodeOutput = bc;
			writer = new BytecodeWriter(symtab, bc);
			bytecodeWriter = writer;
		}
		return writer;
	}

	@Override
	public AttributesVisitor visitAttributes() {
		return attribute -> {
			int nameIndex = symtab.constantPool().add(ConstantUtf8.create(attribute.name()));
			attributes.add(new IndexedAttribute(nameIndex, UnknownAttribute.create(attribute.payload())));
		};
	}

	CodeAttribute compile() {
		MutableConstantPool constantPool = symtab.constantPool();
		try {
			bytecodeWriter.close();
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
		List<TryCatchBlock> tcbs = tryCatchBlocks;
		List<ExceptionTableEntry> exceptionTable = new ArrayList<>(tcbs.size());
		for (TryCatchBlock tcb : tcbs) {
			InstanceType type = tcb.type();
			exceptionTable.add(ExceptionTableEntry.create(
					tcb.start().getPosition(),
					tcb.end().getPosition(),
					tcb.handler().getPosition(),
					type == null ? 0 : constantPool.add(ConstantClass.create(constantPool.add(ConstantUtf8.create(type.internalName()))))
			));
		}
		return CodeAttribute.create(
				maxStack,
				maxLocals,
				new BufferInput(bytecodeOutput.asBuffer()),
				exceptionTable,
				attributes
		);
	}
}
