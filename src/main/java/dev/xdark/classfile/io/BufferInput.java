package dev.xdark.classfile.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;

public final class BufferInput implements BinaryInput {
	private final ByteBuffer buffer;

	public BufferInput(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public long position() {
		return buffer.position();
	}

	@Override
	public void position(long position) {
		ByteBuffer buffer = this.buffer;
		buffer.position((int) position);
	}

	@Override
	public BinaryInput fork(long position) {
		ByteBuffer buffer = this.buffer.duplicate();
		buffer.position((int) position);
		return new BufferInput(buffer);
	}

	@Override
	public BinaryInput duplicate() {
		return new BufferInput(buffer.duplicate());
	}

	@Override
	public void limit(long limit) {
		ByteBuffer buffer = this.buffer;
		buffer.limit(buffer.position() + (int) limit);
	}

	@Override
	public int readByte() {
		return buffer.get();
	}

	@Override
	public int readUnsignedByte() {
		return readByte() & 0xff;
	}

	@Override
	public int readShort() {
		return buffer.getShort();
	}

	@Override
	public int readUnsignedShort() {
		return readShort() & 0xffff;
	}

	@Override
	public char readChar() {
		return buffer.getChar();
	}

	@Override
	public int readInt() {
		return buffer.getInt();
	}

	@Override
	public long readUnsignedInt() {
		return Integer.toUnsignedLong(readInt());
	}

	@Override
	public long readLong() {
		return buffer.getLong();
	}

	@Override
	public float readFloat() {
		return buffer.getFloat();
	}

	@Override
	public double readDouble() {
		return buffer.getDouble();
	}

	@Override
	public int read(byte[] b, int off, int len) {
		ByteBuffer buffer = this.buffer;
		int readable = Math.min(len, buffer.remaining());
		if (readable == 0) return -1;
		buffer.get(b, off, readable);
		return readable;
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws EOFException {
		if (read(b, off, len) != len) {
			throw new EOFException();
		}
	}

	@Override
	public String readUtf() throws IOException {
		int utflen = readUnsignedShort();
		byte[] bytearr;
		char[] chararr;
		bytearr = new byte[utflen];
		chararr = new char[utflen];

		int c, char2, char3;
		int count = 0;
		int chararr_count = 0;

		readFully(bytearr, 0, utflen);

		while (count < utflen) {
			c = (int) bytearr[count] & 0xff;
			if (c > 127) break;
			count++;
			chararr[chararr_count++] = (char) c;
		}

		while (count < utflen) {
			c = (int) bytearr[count] & 0xff;
			switch (c >> 4) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
					/* 0xxxxxxx*/
					count++;
					chararr[chararr_count++] = (char) c;
					break;
				case 12:
				case 13:
					/* 110x xxxx   10xx xxxx*/
					count += 2;
					if (count > utflen)
						throw new UTFDataFormatException(
								"malformed input: partial character at end");
					char2 = (int) bytearr[count - 1];
					if ((char2 & 0xC0) != 0x80)
						throw new UTFDataFormatException(
								"malformed input around byte " + count);
					chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
							(char2 & 0x3F));
					break;
				case 14:
					/* 1110 xxxx  10xx xxxx  10xx xxxx */
					count += 3;
					if (count > utflen)
						throw new UTFDataFormatException(
								"malformed input: partial character at end");
					char2 = (int) bytearr[count - 2];
					char3 = (int) bytearr[count - 1];
					if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
						throw new UTFDataFormatException(
								"malformed input around byte " + (count - 1));
					chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
							((char2 & 0x3F) << 6) |
							((char3 & 0x3F) << 0));
					break;
				default:
					/* 10xx xxxx,  1111 xxxx */
					throw new UTFDataFormatException(
							"malformed input around byte " + count);
			}
		}
		// The number of chars produced may be less than utflen
		return new String(chararr, 0, chararr_count);
	}

	@Override
	public long skip(long bytes) {
		ByteBuffer buffer = this.buffer;
		int skippable = (int) Math.min(buffer.remaining(), bytes);
		buffer.position(buffer.position() + skippable);
		return skippable;
	}

	@Override
	public boolean isReadable(long bytes) {
		return bytes <= buffer.remaining();
	}

	@Override
	public long readableBytes() {
		return buffer.remaining();
	}

	@Override
	public BinaryInput detach() {
		// Even if we don't need to do anything,
		// copy the buffer, that way we can normalise
		// the positions, e.g. for Code attribute.
		ByteBuffer buffer = this.buffer;
		ByteBuffer copy = ByteBuffer.allocate(buffer.remaining());
		copy.put(buffer.slice());
		copy.flip();
		return new BufferInput(copy);
	}
}
