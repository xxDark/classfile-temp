package dev.xdark.classfile.attribute.klass;

import dev.xdark.classfile.attribute.IndexedAttribute;
import dev.xdark.classfile.attribute.klass.impl.RecordComponentImpl;
import dev.xdark.classfile.io.Codec;

import java.util.List;

public interface RecordComponent {
	Codec<RecordComponent> CODEC = RecordComponentImpl.codec();

	int nameIndex();

	int descriptorIndex();

	List<IndexedAttribute> attributes();
}
