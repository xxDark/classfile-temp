package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.BadClassFileFormatException;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

public interface Element {
	Codec<Element> CODEC = Codec.wire(
			Input.wire(reader -> {
				int tag = reader.readUnsignedByte();
				ElementDescriptor<?> descriptor = ElementDescriptor.byTag(tag);
				if (descriptor == null) {
					throw new BadClassFileFormatException(String.format("Unknown annotation tag %d", tag));
				}
				return descriptor.codec().read(reader);
			}, reader -> {
				int tag = reader.readUnsignedByte();
				ElementDescriptor<?> descriptor = ElementDescriptor.byTag(tag);
				if (descriptor == null) {
					throw new BadClassFileFormatException(String.format("Unknown annotation tag %d", tag));
				}
				descriptor.codec().skip(reader);
			}),
			(writer, value) -> {
				ElementDescriptor<?> descriptor = value.descriptor();
				writer.writeByte(descriptor.tag());
				//noinspection unchecked,rawtypes
				((ElementDescriptor) descriptor).codec().write(writer, value);
			}
	);

	ElementDescriptor<?> descriptor();
}
