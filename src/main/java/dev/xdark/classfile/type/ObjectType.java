package dev.xdark.classfile.type;

public interface ObjectType extends ClassType {

	String internalName();

	default String externalName() {
		return Types.externalName(internalName());
	}

	static ObjectType ofInternalName(String name) {
		if (name.charAt(0) == '[') {
			TypeIterator iterator = new TypeIterator(name);
			Type type = iterator.next();
			if (!(type instanceof ArrayType) || iterator.hasNext()) {
				throw new IllegalStateException("Bad array type " + name);
			}
			return (ObjectType) type;
		}
		return InstanceType.ofInternalName(name);
	}

	static ObjectType ofDescriptor(String descriptor) {
		if (descriptor.charAt(0) == '[') {
			return ofInternalName(descriptor);
		}
		return InstanceType.ofDescriptor(descriptor);
	}
}
