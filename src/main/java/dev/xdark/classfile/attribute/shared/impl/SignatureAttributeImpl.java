package dev.xdark.classfile.attribute.shared.impl;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.internal.AttributeHelper;
import dev.xdark.classfile.attribute.shared.SignatureAttribute;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.io.Input;
import dev.xdark.classfile.io.VariableSkip;

public final class SignatureAttributeImpl implements SignatureAttribute {
	private final int signatureIndex;

	public SignatureAttributeImpl(int signatureIndex) {
		this.signatureIndex = signatureIndex;
	}

	@Override
	public int signatureIndex() {
		return signatureIndex;
	}

	@Override
	public AttributeInfo<SignatureAttribute> info() {
		return AttributeInfo.Signature;
	}

	public static Codec<SignatureAttribute> codec() {
		return AttributeHelper.wire(
				Input.wire(reader -> {
					AttributeHelper.checkSize(reader, 2L);
					return new SignatureAttributeImpl(reader.readConstantPoolIndex());
				}, VariableSkip.U4),
				(writer, value) -> writer.writeConstantPoolIndex(value.signatureIndex())
		);
	}
}
