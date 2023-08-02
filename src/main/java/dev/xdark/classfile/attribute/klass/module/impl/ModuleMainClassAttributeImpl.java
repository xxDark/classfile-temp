package dev.xdark.classfile.attribute.klass.module.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.module.ModuleMainClassAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class ModuleMainClassAttributeImpl implements ModuleMainClassAttribute {
	private final int mainClassIndex;

	ModuleMainClassAttributeImpl(int mainClassIndex) {
		this.mainClassIndex = mainClassIndex;
	}

	@Override
	public int mainClassIndex() {
		return mainClassIndex;
	}

	@Override
	public AttributeInfo<ModuleMainClassAttribute> info() {
		return AttributeInfo.ModuleMainClass;
	}

	public static Codec<ModuleMainClassAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 2L);
					return new ModuleMainClassAttributeImpl(reader.readConstantPoolIndex());
				}, VariableSkip.U4),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.mainClassIndex());
				}
		);
	}
}
