package dev.xdark.classfile.io;

public interface BinaryOutput {

	long position();

	void position(long position);

	void writeByte(int value);

	void writeShort(int value);

	void writeChar(char value);

	void writeInt(int value);

	void writeUnsignedInt(long value);

	void writeLong(long value);

	void writeFloat(float value);

	void writeDouble(double value);

	void writeUtf(String utf);

	void write(byte[] b, int off, int len);

	default void write(byte[] b) {
		write(b, 0, b.length);
	}
}
