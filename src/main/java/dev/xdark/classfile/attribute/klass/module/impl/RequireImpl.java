package dev.xdark.classfile.attribute.klass.module.impl;

import dev.xdark.classfile.attribute.klass.module.Require;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.Skip;

public final class RequireImpl implements Require {
	private final int moduleIndex;
	private final int flags;
	private final int versionIndex;

	public RequireImpl(int moduleIndex, int flags, int versionIndex) {
		this.moduleIndex = moduleIndex;
		this.flags = flags;
		this.versionIndex = versionIndex;
	}

	@Override
	public int moduleIndex() {
		return moduleIndex;
	}

	@Override
	public int flags() {
		return flags;
	}

	@Override
	public int versionIndex() {
		return versionIndex;
	}

	public static Codec<Require> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					return new RequireImpl(reader.readConstantPoolIndex(), reader.readAccessFlags(), reader.readConstantPoolIndex());
				}, Skip.exact(6L)),
				(writer, value) -> {
					writer.writeConstantPoolIndex(value.moduleIndex());
					writer.writeAccessFlags(value.flags());
					writer.writeConstantPoolIndex(value.versionIndex());
				}
		);
	}
}
