package dev.xdark.classfile.attribute.klass.module.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.module.ModulePackagesAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class ModulePackagesAttributeImpl implements ModulePackagesAttribute {

	private final int[] packageIndices;

	ModulePackagesAttributeImpl(int[] packageIndices) {
		this.packageIndices = packageIndices;
	}

	@Override
	public int[] packageIndices() {
		return packageIndices;
	}

	@Override
	public AttributeInfo<ModulePackagesAttribute> info() {
		return AttributeInfo.ModulePackages;
	}

	public static Codec<ModulePackagesAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					reader.skipAttributeLength();
					return new ModulePackagesAttributeImpl(AttributeHelper.readUnsignedShorts(reader));
				}, VariableSkip.U4),
				(writer, value) -> {
					AttributeHelper.writeUnsignedShorts(writer, value.packageIndices());
				}
		);
	}
}
