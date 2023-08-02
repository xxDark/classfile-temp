package dev.xdark.classfile.io;

import java.io.IOException;

public interface BinaryOutput {

	long position();

	void position(long position) throws IOException;

	void writeByte(int value) throws IOException;

	void writeShort(int value) throws IOException;

	void writeChar(char value) throws IOException;

	void writeInt(int value) throws IOException;

	void writeUnsignedInt(long value) throws IOException;

	void writeLong(long value) throws IOException;

	void writeFloat(float value) throws IOException;

	void writeDouble(double value) throws IOException;

	void writeUtf(String utf) throws IOException;

	void write(byte[] b, int off, int len) throws IOException;

	default void write(byte[] b) throws IOException {
		write(b, 0, b.length);
	}
}
