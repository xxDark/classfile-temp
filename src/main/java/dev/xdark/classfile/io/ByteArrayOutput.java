package dev.xdark.classfile.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class ByteArrayOutput implements BinaryOutput {
	private final ByteStream stream = new ByteStream();
	private final DataOutputStream dos = new DataOutputStream(stream);

	@Override
	public long position() {
		return stream.pos();
	}

	@Override
	public void position(long position) throws IOException {
		stream.pos(position);
	}

	@Override
	public void writeByte(int value) throws IOException {
		stream.write(value);
	}

	@Override
	public void writeShort(int value) throws IOException {
		dos.writeShort(value);
	}

	@Override
	public void writeChar(char value) throws IOException {
		dos.writeChar(value);
	}

	@Override
	public void writeInt(int value) throws IOException {
		dos.writeInt(value);
	}

	@Override
	public void writeUnsignedInt(long value) throws IOException {
		dos.writeInt((int) value);
	}

	@Override
	public void writeLong(long value) throws IOException {
		dos.writeLong(value);
	}

	@Override
	public void writeFloat(float value) throws IOException {
		dos.writeFloat(value);
	}

	@Override
	public void writeDouble(double value) throws IOException {
		dos.writeDouble(value);
	}

	@Override
	public void writeUtf(String utf) throws IOException {
		dos.writeUTF(utf);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		stream.write(b, off, len);
	}

	public void reset() {
		stream.reset();
	}

	public void transferTo(OutputStream os) throws IOException {
		ByteStream stream = this.stream;
		os.write(stream.buf(), 0, stream.pos());
	}

	public void transferTo(BinaryOutput output) throws IOException {
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
