package dev.xdark.classfile.type;

public interface ClassType extends Type {

	static ClassType ofDescriptor(String descriptor) {
		TypeIterator iterator = new TypeIterator(descriptor);
		Type type = iterator.next();
		if (!(type instanceof ClassType) || iterator.hasNext()) {
			throw new IllegalStateException("Bad class type " + descriptor);
		}
		return (ClassType) type;
	}
}
