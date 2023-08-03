package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.impl.LabelImpl;

public interface Label extends CodeElement {
	int UNSET = -1;

	int getPosition();

	void setPosition(int position);

	int getLineNumber();

	void setLineNumber(int lineNumber);

	static Label create() {
		return new LabelImpl();
	}
}
