package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.Label;

public interface LabelArray {

	Label get(int position);

	default Label get(int position, int offset) {
		return get(position + offset);
	}

	Label create(int position);

	default Label create(int position, int offset) {
		return create(position + offset);
	}
}
