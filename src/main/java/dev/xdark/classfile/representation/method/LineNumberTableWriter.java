package dev.xdark.classfile.representation.method;

import dev.xdark.classfile.attribute.code.LineNumber;
import dev.xdark.classfile.representation.Label;
import dev.xdark.classfile.representation.bytecode.BytecodeAdapter;
import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;

import java.util.List;

public final class LineNumberTableWriter extends BytecodeAdapter {
	private final List<LineNumber> lineNumbers;

	public LineNumberTableWriter(BytecodeVisitor bv, List<LineNumber> lineNumbers) {
		super(bv);
		this.lineNumbers = lineNumbers;
	}

	@Override
	public void label(Label label) {
		super.label(label);
		int line = label.getLineNumber();
		if (line != Label.UNSET) {
			lineNumbers.add(LineNumber.create(label.getPosition(), line));
		}
	}
}
