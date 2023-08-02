package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.EnclosingMethodAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public  final class EnclosingMethodAttributeImpl implements EnclosingMethodAttribute {

	private final int classIndex;
	private final int methodIndex;

	public EnclosingMethodAttributeImpl(int classIndex, int methodIndex) {
		this.classIndex = classIndex;
		this.methodIndex = methodIndex;
	}

	@Override
	public int classIndex() {
		return classIndex;
	}

	@Override
	public int methodIndex() {
		return methodIndex;
	}

	@Override
	public AttributeInfo<EnclosingMethodAttribute> info() {
		return AttributeInfo.EnclosingMethod;
	}

	public static Codec<EnclosingMethodAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 4L);
					return new EnclosingMethodAttributeImpl(reader.readConstantPoolIndex(), reader.readConstantPoolIndex());
				}, VariableSkip.U2),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.classIndex());
					writer.writeConstantPoolIndex(value.methodIndex());
				}
		);
	}
}
