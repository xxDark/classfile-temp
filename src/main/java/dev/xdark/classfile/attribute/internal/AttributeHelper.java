package dev.xdark.classfile.attribute.internal;

import dev.xdark.classfile.io.UncheckedIOException;
import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.Attribute;
import dev.xdark.classfile.io.ClassWriter;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.Output;
import dev.xdark.classfile.io.Read;
import dev.xdark.classfile.io.Skip;

import java.util.ArrayList;
import java.util.List;

public final class AttributeHelper {

	private AttributeHelper() {
	}

	public static <A extends Attribute> Codec<A> wire(Input<A> input, Output<A> output) {
		return Codec.wire(input, write(output));
	}

	public static void checkSize(ClassReader cr, long size) {
		checkSize(cr.readAttributeLength(), size);
	}

	public static void checkSize(long actualSize, long expectedSize) {
		if (actualSize != expectedSize) {
			throw new UncheckedIOException(String.format("Attribute size mismatch: %d != %d", actualSize, expectedSize));
		}
	}

	public static void checkAtLeast(long actualSize, long expectedSize) {
		if (actualSize < expectedSize) {
			throw new UncheckedIOException(String.format("Attribute size is too small: %d < %d", actualSize, expectedSize));
		}
	}

	public static int[] readUnsignedShorts(ClassReader cr, int count) {
		int[] arr = new int[count];
		for (int i = 0; i < count; i++) {
			arr[i] = cr.readUnsignedShort();
		}
		return arr;
	}

	public static int[] readUnsignedShorts(ClassReader cr) {
		return readUnsignedShorts(cr, cr.readUnsignedShort());
	}

	public static void writeUnsignedShorts(ClassWriter writer, int[] arr) {
		writer.writeShort(arr.length);
		for (int i : arr) {
			writer.writeShort(i);
		}
	}

	public static Skip skipUnsignedShorts() {
		return reader -> {
			int count = reader.readUnsignedShort();
			reader.skipBytes(count * 2L);
		};
	}

	public static <T> List<T> readList(ClassReader reader, int count, Read<T> codec) {
		List<T> list = new ArrayList<>();
		while (count-- != 0) {
			list.add(codec.read(reader));
		}
		return list;
	}

	public static <T> List<T> readList(ClassReader reader, Read<T> codec) {
		return readList(reader, reader.readUnsignedShort(), codec);
	}

	public static <T> void writeList(ClassWriter writer, List<T> list, Output<T> output) {
		writer.writeShort(list.size());
		for (T item : list) {
			output.write(writer, item);
		}
	}

	public static Skip skipN(Skip skip) {
		return reader -> {
			int n = reader.readUnsignedShort();
			while (n-- != 0) {
				skip.skip(reader);
			}
		};
	}

	public static <A extends Attribute> Output<A> write(Output<A> output) {
		return (writer, value) -> {
			long position = writer.position();
			writer.writeInt(0);
			output.write(writer, value);
			long newPosition = writer.position();
			long written = newPosition - position - 4;
			writer.position(position);
			writer.writeAttributeLength(written);
			writer.position(newPosition);
		};
	}
}
