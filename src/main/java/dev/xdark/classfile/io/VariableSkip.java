package dev.xdark.classfile.io;

import dev.xdark.classfile.ClassReader;

import java.io.IOException;

public final class VariableSkip implements Skip {
	public static final VariableSkip
			U2 = new VariableSkip(BinaryInput::readUnsignedShort),
			U4 = new VariableSkip(BinaryInput::readUnsignedInt);
	private final VariableRead toSkip;

	VariableSkip(VariableRead toSkip) {
		this.toSkip = toSkip;
	}

	@Override
	public void skip(ClassReader reader) throws IOException {
		long toSkip = this.toSkip.read(reader);
		reader.skipBytes(toSkip);
	}

	@FunctionalInterface
	public interface VariableRead {

		long read(BinaryInput input) throws IOException;
	}
}
