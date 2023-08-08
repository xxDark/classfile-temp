package dev.xdark.classfile.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class ByteArrayOutput implements BinaryOutput {
	private final ByteStream stream = new ByteStream();

	@Override
	public long position() {
		return stream.pos();
	}

	@Override
	public void position(long position) {
		stream.pos(position);
	}

	@Override
	public void writeByte(int value) {
		stream.write(value);
	}

	@Override
	public void writeShort(int value) {
		ByteStream s = stream;
		s.write((value >>> 8) & 0xff);
		s.write(value & 0xff);
	}

	@Override
	public void writeChar(char value) {
		ByteStream s = stream;
		s.write((value >>> 8) & 0xff);
		s.write(value & 0xff);
	}

	@Override
	public void writeInt(int value) {
		ByteStream s = stream;
		s.write((value >>> 24) & 0xff);
		s.write((value >>> 16) & 0xff);
		s.write((value >>> 8) & 0xff);
		s.write(value & 0xff);
	}

	@Override
	public void writeUnsignedInt(long value) {
		writeInt((int) value);
	}

	@Override
	public void writeLong(long value) {
		ByteStream s = stream;
		s.write((byte) (value >>> 56));
		s.write((byte) (value >>> 48));
		s.write((byte) (value >>> 40));
		s.write((byte) (value >>> 32));
		s.write((byte) (value >>> 24));
		s.write((byte) (value >>> 16));
		s.write((byte) (value >>> 8));
		s.write((byte) value);
	}

	@Override
	public void writeFloat(float value) {
		writeInt(Float.floatToIntBits(value));
	}

	@Override
	public void writeDouble(double value) {
		writeLong(Double.doubleToLongBits(value));
	}

	@Override
	public void writeUtf(String utf) {
		int strlen = utf.length();
		int utflen = 0;
		int c, count = 0;

		/* use charAt instead of copying String to char array */
		for (int i = 0; i < strlen; i++) {
			c = utf.charAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				utflen++;
			} else if (c > 0x07FF) {
				utflen += 3;
			} else {
				utflen += 2;
			}
		}

		if (utflen > 65535)
			throw new IllegalStateException(
					"encoded string too long: " + utflen + " bytes");

		byte[] bytearr = new byte[utflen + 2];


		bytearr[count++] = (byte) ((utflen >>> 8) & 0xFF);
		bytearr[count++] = (byte) (utflen & 0xFF);

		int i;
		for (i = 0; i < strlen; i++) {
			c = utf.charAt(i);
			if (!((c >= 0x0001) && (c <= 0x007F))) break;
			bytearr[count++] = (byte) c;
		}

		for (; i < strlen; i++) {
			c = utf.charAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				bytearr[count++] = (byte) c;

			} else if (c > 0x07FF) {
				bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
				bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
				bytearr[count++] = (byte) (0x80 | (c & 0x3F));
			} else {
				bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
				bytearr[count++] = (byte) (0x80 | (c & 0x3F));
			}
		}
		stream.write(bytearr, 0, utflen + 2);
	}

	@Override
	public void write(byte[] b, int off, int len) {
		stream.write(b, off, len);
	}

	public void reset() {
		stream.reset();
	}

	public void transferTo(OutputStream os) throws IOException {
		ByteStream stream = this.stream;
		os.write(stream.buf(), 0, stream.pos());
	}

	public void transferTo(BinaryOutput output) {
		ByteStream stream = this.stream;
		output.write(stream.buf(), 0, stream.pos());
	}

	public ByteBuffer asBuffer() {
		ByteStream stream = this.stream;
		return ByteBuffer.wrap(stream.buf(), 0, stream.pos());
	}

	public byte[] toByteArray() {
		return stream.toByteArray();
	}

	private static final class ByteStream extends ByteArrayOutputStream {

		byte[] buf() {
			return buf;
		}

		int pos() {
			return count;
		}

		void pos(long pos) {
			count = (int) pos;
		}
	}
}
