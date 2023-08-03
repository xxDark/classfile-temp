package dev.xdark.classfile.type;

public final class ArrayType implements ObjectType {

	private final int dimensions;
	private final ClassType component;
	private String descriptor;

	private ArrayType(int dimensions, ClassType component) {
		this.dimensions = dimensions;
		this.component = component;
	}

	@Override
	public String descriptor() {
		String descriptor = this.descriptor;
		if (descriptor == null) {
			descriptor = '[' + component.descriptor();
			this.descriptor = descriptor;
		}
		return descriptor;
	}

	@Override
	public String internalName() {
		return descriptor();
	}

	public int dimensions() {
		return dimensions;
	}

	public ClassType component() {
		return component;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ArrayType arrayType = (ArrayType) o;

		if (dimensions != arrayType.dimensions) return false;
		return component.equals(arrayType.component);
	}

	@Override
	public int hashCode() {
		int result = dimensions;
		result = 31 * result + component.hashCode();
		return result;
	}

	public static ArrayType create(ClassType component) {
		int dimensions;
		if (component instanceof ArrayType) {
			dimensions = ((ArrayType) component).dimensions + 1;
		} else {
			dimensions = 1;
		}
		return new ArrayType(dimensions, component);
	}

	public static ArrayType ofInternalName(String name) {
		parse:
		{
			if (name.charAt(0) != '[') {
				break parse;
			}
			TypeIterator iterator = new TypeIterator(name);
			Type type = iterator.next();
			if (!(type instanceof ArrayType) || iterator.hasNext()) {
				break parse;
			}
			return (ArrayType) type;
		}
		throw new IllegalStateException("Bad array type " + name);
	}

	public static ArrayType ofClass(Class<?> c) {
		Class<?> ct = c.getComponentType();
		if (ct == null) {
			throw new IllegalArgumentException(String.format("Not an array %s", c));
		}
		return create(ClassType.ofClass(ct));
	}
}
