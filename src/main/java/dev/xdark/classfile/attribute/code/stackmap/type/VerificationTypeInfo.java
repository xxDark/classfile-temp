package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.io.UncheckedIOException;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;

public interface VerificationTypeInfo {
	Codec<VerificationTypeInfo> CODEC = Codec.wire(
			Input.wire(reader -> {
				int rawId = reader.readUnsignedByte();
				VerificationTypeDescriptor<?> descriptor = VerificationTypeDescriptor.byId(rawId);
				if (descriptor == null) {
					throw new UncheckedIOException(String.format("Unknown verification type info %d", rawId));
				}
				return descriptor.codec().read(reader);
			}, reader -> {
				int rawId = reader.readUnsignedByte();
				VerificationTypeDescriptor<?> descriptor = VerificationTypeDescriptor.byId(rawId);
				if (descriptor == null) {
					throw new UncheckedIOException(String.format("Unknown verification type info %d", rawId));
				}
				descriptor.codec().skip(reader);
			}),
			(writer, value) -> {
				VerificationTypeDescriptor<?> descriptor = value.descriptor();
				writer.writeByte(descriptor.id());
				//noinspection unchecked,rawtypes
				((VerificationTypeDescriptor) descriptor).codec().write(writer, value);
			}
	);

	VerificationTypeDescriptor<?> descriptor();
}
