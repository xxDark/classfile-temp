package dev.xdark.classfile.type;

public interface Type {

	String descriptor();

	static Type ofDescriptor(String descriptor) {
		TypeIterator iterator = new TypeIterator(descriptor);
		Type type = iterator.next();
		if (iterator.hasNext()) {
			throw new IllegalStateException("Bad type " + descriptor);
		}
		return type;
	}
}
