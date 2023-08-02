package dev.xdark.classfile.attribute.internal;

import dev.xdark.classfile.ClassReader;
import dev.xdark.classfile.attribute.Attribute;
import dev.xdark.classfile.io.BinaryInput;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public class PayloadAttribute implements Attribute {
	final BinaryInput input;

	protected PayloadAttribute(BinaryInput input) {
		this.input = input;
	}

	public final BinaryInput payload() {
		return input.duplicate();
	}

	public static <A extends Attribute> Codec<A> makeCodec(AttributeCreator<A> creator) {
		return Codec.wire(
				Input.wire(reader -> {
					long length = reader.readAttributeLength();
					long position = reader.position();
					reader.skipBytes(length);
					ClassReader payload = reader.fork(position);
					payload.limit(length);
					return creator.create(payload.detach());
				}, VariableSkip.U4),
				(writer, value) -> {
					PayloadAttribute impl = (PayloadAttribute) value;
					BinaryInput reader = impl.input;
					writer.writeAttributeLength(reader.readableBytes());
					byte[] buf = new byte[1024];
					int r;
					while ((r = reader.read(buf)) != -1) {
						writer.write(buf, 0, r);
					}
				}
		);
	}

	public interface AttributeCreator<A extends Attribute> {

		A create(BinaryInput input);
	}
}
