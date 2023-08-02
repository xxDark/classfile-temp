package dev.xdark.classfile.attribute.shared.annotation;

import dev.xdark.classfile.attribute.shared.annotation.impl.ElementAnnotationImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementArrayImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementBooleanImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementByteImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementCharImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementClassImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementDoubleImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementEnumImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementFloatImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementIntImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementLongImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementShortImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.ElementStringImpl;
import dev.xdark.classfile.io.Codec;

public final class ElementDescriptor<E extends Element> {
	private static final ElementDescriptor<?>[] TABLE = new ElementDescriptor['Z' + 1];
	public static final ElementDescriptor<ElementByte> BYTE = create('B', ElementByteImpl.codec());
	public static final ElementDescriptor<ElementChar> CHAR = create('C', ElementCharImpl.codec());
	public static final ElementDescriptor<ElementDouble> DOUBLE = create('D', ElementDoubleImpl.codec());
	public static final ElementDescriptor<ElementFloat> FLOAT = create('F', ElementFloatImpl.codec());
	public static final ElementDescriptor<ElementInt> INT = create('I', ElementIntImpl.codec());
	public static final ElementDescriptor<ElementLong> LONG = create('J', ElementLongImpl.codec());
	public static final ElementDescriptor<ElementShort> SHORT = create('S', ElementShortImpl.codec());
	public static final ElementDescriptor<ElementBoolean> BOOLEAN = create('Z', ElementBooleanImpl.codec());
	public static final ElementDescriptor<ElementString> STRING = create('s', ElementStringImpl.codec());
	public static final ElementDescriptor<ElementEnum> ENUM = create('e', ElementEnumImpl.codec());
	public static final ElementDescriptor<ElementClass> CLASS = create('c', ElementClassImpl.codec());
	public static final ElementDescriptor<ElementAnnotation> ANNOTATION = create('@', ElementAnnotationImpl.codec());
	public static final ElementDescriptor<ElementArray> ARRAY = create('[', ElementArrayImpl.codec());
	private final int tag;
	private final Codec<E> codec;

	private ElementDescriptor(int tag, Codec<E> codec) {
		this.tag = tag;
		this.codec = codec;
	}

	public int tag() {
		return tag;
	}

	public Codec<E> codec() {
		return codec;
	}

	public static ElementDescriptor<?> byTag(int tag) {
		return TABLE[tag];
	}

	private static <E extends Element> ElementDescriptor<E> create(int tag, Codec<E> codec) {
		ElementDescriptor<E> descriptor = new ElementDescriptor<>(tag, codec);
		TABLE[tag] = descriptor;
		return descriptor;
	}
}
