package dev.xdark.classfile.type;

public final class InstanceType implements ObjectType {

	private String descriptor;
	private String internalName;

	private InstanceType(String descriptor, String internalName) {
		this.descriptor = descriptor;
		this.internalName = internalName;
	}

	@Override
	public String descriptor() {
		String descriptor = this.descriptor;
		if (descriptor == null) {
			String in = internalName;
			descriptor = 'L' + in + ';';
			this.descriptor = descriptor;
		}
		return descriptor;
	}

	@Override
	public String internalName() {
		String in = internalName;
		if (in == null) {
			String desc = descriptor;
			in = desc.substring(1, desc.length() - 1);
			internalName = in;
		}
		return in;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InstanceType that = (InstanceType) o;

		return descriptor().equals(that.descriptor());
	}

	@Override
	public int hashCode() {
		return descriptor().hashCode();
	}

	public static InstanceType ofDescriptor(String descriptor) {
		return new InstanceType(descriptor, null);
	}

	public static InstanceType ofInternalName(String internalName) {
		return new InstanceType(null, internalName);
	}

	public static InstanceType ofExternalName(String externalName) {
		return ofInternalName(Types.internalName(externalName));
	}

	public static InstanceType ofClass(Class<?> c) {
		if (c.isPrimitive() || c.isArray()) {
			throw new IllegalArgumentException("Not an instance " + c);
		}
		return ofExternalName(c.getName());
	}
}
