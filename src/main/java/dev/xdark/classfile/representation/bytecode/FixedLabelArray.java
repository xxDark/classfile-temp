package dev.xdark.classfile.representation.bytecode;

import dev.xdark.classfile.representation.Label;

public final class FixedLabelArray implements LabelArray {
	private final Label[] labels;

	public FixedLabelArray(int size) {
		labels = new Label[size];
	}

	@Override
	public Label get(int position) {
		return labels[position];
	}

	@Override
	public Label create(int position) {
		Label[] labels = this.labels;
		Label label = labels[position];
		if (label == null) {
			label = Label.create();
			label.setPosition(position);
			labels[position] = label;
		}
		return label;
	}
}
