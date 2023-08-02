package dev.xdark.classfile.io;

import java.io.IOException;

public interface BinaryInput {

	long position();

	void position(long position) throws IOException;

	BinaryInput fork(long position) throws IOException;

	BinaryInput duplicate();

	void limit(long limit) throws IOException;

	int readByte() throws IOException;

	int readUnsignedByte() throws IOException;

	int readShort() throws IOException;

	int readUnsignedShort() throws IOException;

	char readChar() throws IOException;

	int readInt() throws IOException;

	long readUnsignedInt() throws IOException;

	long readLong() throws IOException;

	float readFloat() throws IOException;

	double readDouble() throws IOException;

	int read(byte[] b, int off, int len) throws IOException;

	void readFully(byte[] b, int off, int len) throws IOException;

	String readUtf() throws IOException;

	long skip(long bytes) throws IOException;

	boolean isReadable(long bytes);

	long readableBytes();

	/**
	 * Optionally detaches this input.
	 * <br>
	 * E.g. if reading from a file,
	 * this method should copy contents of this input
	 * to the heap so i.e. if {@link BinaryInput#close()} is called,
	 * whatever was left in this input would still be valid
	 * and available for read.
	 *
	 * @return Detached input.
	 * @throws IOException If any I/O error occurs.
	 */
	BinaryInput detach() throws IOException;

	default int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	default void readFully(byte[] b) throws IOException {
		readFully(b, 0, b.length);
	}

	default void skipBytes(long bytes) throws IOException {
		long skippedTotal = 0L;
		long bookkeep = bytes;
		while (bytes > 0L) {
			long skipped = skip(bytes);
			if (skipped == 0L) {
				throw new IOException(String.format("Did not skip all bytes: %d > %d", bookkeep, skippedTotal));
			}
			bytes -= skipped;
			skippedTotal += skipped;
		}
	}
}
