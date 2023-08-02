package dev.xdark.classfile.attribute;

public final class IndexedAttribute {
	private final int nameIndex;
	private final Attribute attribute;

	public IndexedAttribute(int nameIndex, Attribute attribute) {
		this.nameIndex = nameIndex;
		this.attribute = attribute;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public Attribute getAttribute() {
		return attribute;
	}
}
