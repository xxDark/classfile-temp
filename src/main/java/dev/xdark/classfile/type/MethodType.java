package dev.xdark.classfile.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class MethodType implements Type {
	private final ClassType returnType;
	private final List<ClassType> parameterTypes;
	private String descriptor;

	private MethodType(ClassType returnType, List<ClassType> parameterTypes) {
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
	}

	public ClassType returnType() {
		return returnType;
	}

	public List<ClassType> parameterTypes() {
		return parameterTypes;
	}

	@Override
	public String descriptor() {
		String descriptor = this.descriptor;
		if (descriptor == null) {
			StringBuilder builder = new StringBuilder();
			builder.append('(');
			for (ClassType type : parameterTypes) {
				builder.append(type.descriptor());
			}
			descriptor = builder.append(')').append(returnType.descriptor()).toString();
			this.descriptor = descriptor;
		}
		return descriptor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MethodType that = (MethodType) o;

		if (!returnType.equals(that.returnType)) return false;
		return parameterTypes.equals(that.parameterTypes);
	}

	@Override
	public int hashCode() {
		int result = returnType.hashCode();
		result = 31 * result + parameterTypes.hashCode();
		return result;
	}

	public static MethodType create(ClassType returnType, List<ClassType> parameterTypes) {
		return new MethodType(returnType, parameterTypes);
	}

	public static MethodType create(ClassType returnType, Stream<ClassType> parameterTypes) {
		return create(returnType, parameterTypes.collect(Collectors.toList()));
	}

	public static MethodType create(ClassType returnType, ClassType... parameterTypes) {
		return create(returnType, Arrays.asList(parameterTypes));
	}

	public static MethodType create(Class<?> returnType, Class<?>... parameterTypes) {
		return create(ClassType.ofClass(returnType), Arrays.stream(parameterTypes).map(ClassType::ofClass));
	}

	public static MethodType ofDescriptor(String descriptor) {
		TypeIterator iterator = new TypeIterator(descriptor);
		Type type = iterator.next();
		if (!(type instanceof MethodType) || iterator.hasNext()) {
			throw new IllegalStateException("Bad method type " + descriptor);
		}
		return (MethodType) type;
	}
}
