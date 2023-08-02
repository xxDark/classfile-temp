package dev.xdark.classfile.attribute.shared;

import dev.xdark.classfile.attribute.AttributeInfo;
import dev.xdark.classfile.attribute.SpecAttribute;
import dev.xdark.classfile.attribute.shared.impl.SignatureAttributeImpl;

public interface SignatureAttribute extends SpecAttribute {

	int signatureIndex();

	@Override
	AttributeInfo<SignatureAttribute> info();

	static SignatureAttribute create(int signatureIndex) {
		return new SignatureAttributeImpl(signatureIndex);
	}
}
