package dev.xdark.classfile.type;

import java.util.EnumMap;
import java.util.Map;

public final class PrimitiveType implements ClassType {
	private static final Map<PrimitiveKind, PrimitiveType> MAP = new EnumMap<>(PrimitiveKind.class);
	public static final PrimitiveType
			T_BOOLEAN = create("Z", PrimitiveKind.T_BOOLEAN, "boolean"),
			T_CHAR = create("C", PrimitiveKind.T_CHAR, "char"),
			T_FLOAT = create("F", PrimitiveKind.T_FLOAT, "float"),
			T_DOUBLE = create("D", PrimitiveKind.T_DOUBLE, "double"),
			T_BYTE = create("B", PrimitiveKind.T_BYTE, "byte"),
			T_SHORT = create("S", PrimitiveKind.T_SHORT, "short"),
			T_INT = create("I", PrimitiveKind.T_INT, "int"),
			T_LONG = create("J", PrimitiveKind.T_LONG, "long"),
			T_VOID = create("V", PrimitiveKind.T_VOID, "void");

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
		return MAP.get(kind);
	}

	private static PrimitiveType create(String descriptor, PrimitiveKind kind, String name) {
		PrimitiveType pt = new PrimitiveType(descriptor, kind, name);
		MAP.put(kind, pt);
		return pt;
	}
}
