package dev.xdark.classfile.io;

public interface BinaryInput {

	long position();

	void position(long position);

	BinaryInput fork(long position);

	BinaryInput duplicate();

	void limit(long limit);

	int readByte();

	int readUnsignedByte();

	int readShort();

	int readUnsignedShort();

	char readChar();

	int readInt();

	long readUnsignedInt();

	long readLong();

	float readFloat();

	double readDouble();

	int read(byte[] b, int off, int len);

	void readFully(byte[] b, int off, int len);

	String readUtf();

	long skip(long bytes);

	boolean isReadable(long bytes);

	long readableBytes();

	/**
	 * Optionally detaches this input.
	 * <br>
	 * E.g. if reading from a file,
	 * this method should copy contents of this input
	 * to the heap.
	 *
	 * @return Detached input.
	 */
	BinaryInput detach();

	default int read(byte[] b) {
		return read(b, 0, b.length);
	}

	default void readFully(byte[] b) {
		readFully(b, 0, b.length);
	}

	default void skipBytes(long bytes) {
		long skippedTotal = 0L;
		long bookkeep = bytes;
		while (bytes > 0L) {
			long skipped = skip(bytes);
			if (skipped == 0L) {
				throw new IllegalStateException(String.format("Did not skip all bytes: %d > %d", bookkeep, skippedTotal));
			}
			bytes -= skipped;
			skippedTotal += skipped;
		}
	}
}
