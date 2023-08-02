package dev.xdark.classfile.attribute.klass.module.impl;

import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.module.Open;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class OpenImpl implements Open {

	private final int packageIndex;
	private final int flags;
	private final int[] moduleIndices;

	public OpenImpl(int packageIndex, int flags, int[] moduleIndices) {
		this.packageIndex = packageIndex;
		this.flags = flags;
		this.moduleIndices = moduleIndices;
	}

	@Override
	public int packageIndex() {
		return packageIndex;
	}

	@Override
	public int flags() {
		return flags;
	}

	@Override
	public int[] moduleIndices() {
		return moduleIndices;
	}

	public static Codec<Open> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					return new OpenImpl(reader.readConstantPoolIndex(), reader.readAccessFlags(), AttributeHelper.readUnsignedShorts(reader));
				}, ExactSkip.U4.then(AttributeHelper.skipUnsignedShorts())),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.packageIndex());
					writer.writeAccessFlags(value.flags());
					AttributeHelper.writeUnsignedShorts(writer, value.moduleIndices());
				}
		);
	}
}
