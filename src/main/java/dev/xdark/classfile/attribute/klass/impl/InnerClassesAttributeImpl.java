package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.InnerClass;
import dev.xdark.classfile.attribute.klass.InnerClassesAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

import java.util.ArrayList;
import java.util.List;

public final class InnerClassesAttributeImpl implements InnerClassesAttribute {

	private final List<InnerClass> innerClasses;

	public InnerClassesAttributeImpl(List<InnerClass> innerClasses) {
		this.innerClasses = innerClasses;
	}

	@Override
	public List<InnerClass> innerClasses() {
		return innerClasses;
	}

	@Override
	public AttributeInfo<InnerClassesAttribute> info() {
		return AttributeInfo.InnerClasses;
	}

	public static Codec<InnerClassesAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					long size = reader.readAttributeLength();
					AttributeHelper.checkAtLeast(size, 2L);
					int count = reader.readUnsignedShort();
					AttributeHelper.checkSize(size - 2L, count * 8L);
					List<InnerClass> innerClasses = new ArrayList<>();
					while (count-- != 0) {
						innerClasses.add(InnerClass.CODEC.read(reader));
					}
					return new InnerClassesAttributeImpl(innerClasses);
				}, VariableSkip.U4),
				(writer, value) -> {
					List<InnerClass> innerClasses = value.innerClasses();
					writer.writeShort(innerClasses.size());
					for (InnerClass cls : innerClasses) {
						InnerClass.CODEC.write(writer, cls);
					}
				}
		);
	}
}
