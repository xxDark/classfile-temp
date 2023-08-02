package dev.xdark.classfile.attribute.klass.impl;

import dev.xdark.classfile.attribute.klass.BootstrapMethod;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

public final class BootstrapMethodImpl implements BootstrapMethod {
	private final int referenceIndex;
	private final int[] argumentIndices;

	public BootstrapMethodImpl(int referenceIndex, int[] argumentIndices) {
		this.referenceIndex = referenceIndex;
		this.argumentIndices = argumentIndices;
	}

	@Override
	public int referenceIndex() {
		return referenceIndex;
	}

	@Override
	public int[] argumentIndices() {
		return argumentIndices;
	}

	public static Codec<BootstrapMethod> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					int ref = reader.readConstantPoolIndex();
					int argumentCount = reader.readUnsignedShort();
					int[] argumentIndices = new int[argumentCount];
					for (int i = 0; i < argumentCount; i++) {
						argumentIndices[i] = reader.readConstantPoolIndex();
					}
					return new BootstrapMethodImpl(ref, argumentIndices);
				}, reader -> {
					reader.skipConstantPoolIndex();
					reader.skipBytes(reader.readUnsignedShort() * 2L);
				}),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.referenceIndex());
					int[] indices = value.argumentIndices();
					writer.writeShort(indices.length);
					for (int index : indices) {
						writer.writeConstantPoolIndex(index);
					}
				}
		);
	}
}
