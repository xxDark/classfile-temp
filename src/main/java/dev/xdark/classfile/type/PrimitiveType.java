package dev.xdark.classfile.type;

import dev.xdark.classfile.util.ConstantMap;

import java.util.EnumMap;
import java.util.Map;

public final class PrimitiveType implements ClassType {
	private static final Map<PrimitiveKind, PrimitiveType> BY_KIND = new EnumMap<>(PrimitiveKind.class);
	private static final ConstantMap<Class<?>, PrimitiveType> BY_CLASS = new ConstantMap<>(33, x -> {
		String name = x.getName();
		int h = 31;
		int length = name.length();
		for (int j = length; j != 1; ) {
			char b = name.charAt(--j);
			char a = name.charAt(j);
			h = h * 8191 + (a << (b & 31));
		}
		return h | h << length;
	});
	public static final PrimitiveType
			T_BOOLEAN = create("Z", PrimitiveKind.T_BOOLEAN, boolean.class),
			T_CHAR = create("C", PrimitiveKind.T_CHAR, char.class),
			T_FLOAT = create("F", PrimitiveKind.T_FLOAT, float.class),
			T_DOUBLE = create("D", PrimitiveKind.T_DOUBLE, double.class),
			T_BYTE = create("B", PrimitiveKind.T_BYTE, byte.class),
			T_SHORT = create("S", PrimitiveKind.T_SHORT, short.class),
			T_INT = create("I", PrimitiveKind.T_INT, int.class),
			T_LONG = create("J", PrimitiveKind.T_LONG, long.class),
			T_VOID = create("V", PrimitiveKind.T_VOID, void.class);

	private final String descriptor;
	private final PrimitiveKind kind;
	private final String name;

	private PrimitiveType(String descriptor, PrimitiveKind kind, String name) {
		this.descriptor = descriptor;
		this.kind = kind;
		this.name = name;
	}

	public PrimitiveKind kind() {
		return kind;
	}

	public String name() {
		return name;
	}

	@Override
	public String descriptor() {
		return descriptor;
	}

	public static PrimitiveType byKind(PrimitiveKind kind) {
		return BY_KIND.get(kind);
	}

	public static PrimitiveType ofClass(Class<?> c) {
		if (!c.isPrimitive()) {
			throw new IllegalArgumentException(String.format("Not a primitive %s", c));
		}
		return BY_CLASS.get(c);
	}

	private static PrimitiveType create(String descriptor, PrimitiveKind kind, Class<?> cls) {
		PrimitiveType pt = new PrimitiveType(descriptor, kind, cls.getName());
		BY_KIND.put(kind, pt);
		BY_CLASS.put(cls, pt);
		return pt;
	}
}
