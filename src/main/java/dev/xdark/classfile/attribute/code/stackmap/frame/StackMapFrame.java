package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.io.UncheckedIOException;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

public interface StackMapFrame {
	Codec<StackMapFrame> CODEC = Codec.wire(
			Input.wire(reader -> {
				long pos = reader.position();
				int tag = reader.readUnsignedByte();
				FrameType<?> type = FrameType.byType(tag);
				if (type == null) {
					throw new UncheckedIOException(String.format("Unknown frame type %d", tag));
				}
				reader.position(pos);
				return type.codec().read(reader);
			}, reader -> {
				long pos = reader.position();
				int tag = reader.readUnsignedByte();
				FrameType<?> type = FrameType.byType(tag);
				if (type == null) {
					throw new UncheckedIOException(String.format("Unknown frame type %d", tag));
				}
				reader.position(pos);
				type.codec().skip(reader);
			}),
			(writer, value) -> {
				//noinspection unchecked,rawtypes
				((FrameType) value.type()).codec().write(writer, value);
			}
	);

	int offsetDelta();

	FrameType<?> type();
}
