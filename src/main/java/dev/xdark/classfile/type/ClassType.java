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

	static ClassType ofClass(Class<?> c) {
		Class<?> ct = c.getComponentType();
		if (ct != null) {
			return ArrayType.create(ofClass(ct));
		}
		if (c.isPrimitive()) {
			return PrimitiveType.ofClass(c);
		}
		return InstanceType.ofExternalName(c.getName());
	}
}
