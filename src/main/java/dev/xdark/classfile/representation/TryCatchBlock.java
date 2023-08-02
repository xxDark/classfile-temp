package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.impl.TryCatchBlockImpl;
import dev.xdark.classfile.type.InstanceType;

public interface TryCatchBlock {

	Label start();

	Label end();

	Label handler();

	InstanceType type();

	void accept(CodeVisitor visitor);

	static TryCatchBlock create(Label start, Label end, Label handler, InstanceType type) {
		return new TryCatchBlockImpl(start, end, handler, type);
	}
}
