package dev.xdark.classfile.attribute.klass.module.impl;

import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.klass.module.Provide;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ProvideImpl implements Provide {

	private final int serviceIndex;
	private final int[] implementationIndices;

	public ProvideImpl(int serviceIndex, int[] implementationIndices) {
		this.serviceIndex = serviceIndex;
		this.implementationIndices = implementationIndices;
	}

	@Override
	public int serviceIndex() {
		return serviceIndex;
	}

	@Override
	public int[] implementationIndices() {
		return implementationIndices;
	}

	public static Codec<Provide> codec() {
		return Codec.wire(
				Input.wire(
						reader -> new ProvideImpl(reader.readConstantPoolIndex(), AttributeHelper.readUnsignedShorts(reader)),
						ExactSkip.U2.then(AttributeHelper.skipUnsignedShorts())
				),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.serviceIndex());
					AttributeHelper.writeUnsignedShorts(writer, value.implementationIndices());
				}
		);
	}
}
