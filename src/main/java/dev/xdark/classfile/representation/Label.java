package dev.xdark.classfile.representation;

import dev.xdark.classfile.representation.impl.LabelImpl;

public interface Label extends CodeElement {

	int getPosition();

	void setPosition(int position);

	static Label create() {
		return new LabelImpl();
	}
}
