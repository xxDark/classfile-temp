package dev.xdark.classfile.impl;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.ExactSkip;
import dev.xdark.classfile.io.Input;

public final class ClassFileVersionImpl implements ClassFileVersion {
	private final int major;
	private final int minor;

	public ClassFileVersionImpl(int major, int minor) {
		this.major = major;
		this.minor = minor;
	}

	@Override
	public int major() {
		return major;
	}

	@Override
	public int minor() {
		return major;
	}

	@Override
	public int pack() {
		return major | minor << 16;
	}

	@Override
	public ClassFileVersion withMajor(int major) {
		return new ClassFileVersionImpl(major, minor);
	}

	@Override
	public ClassFileVersion withMinor(int minor) {
		return new ClassFileVersionImpl(major, minor);
	}

	@Override
	public ClassFileVersion withPreviewFeatures() {
		return new ClassFileVersionImpl(major, 0xFFFF);
	}

	@Override
	public ClassFileVersion dropPreviewFeatures() {
		return new ClassFileVersionImpl(major, 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ClassFileVersionImpl that = (ClassFileVersionImpl) o;

		if (major != that.major) return false;
		return minor == that.minor;
	}

	@Override
	public int hashCode() {
		return major | minor << 16;
	}

	public static Codec<ClassFileVersion> codec() {
		return Codec.wire(
				Input.wire(reader -> {
					int minor = reader.readUnsignedShort();
					int major = reader.readUnsignedShort();
					return ClassFileVersion.of(major, minor);
				}, ExactSkip.U4),
				(writer, value) -> writer.writeInt(value.pack())
		);
	}
}
