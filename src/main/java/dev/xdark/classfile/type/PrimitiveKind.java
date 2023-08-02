package dev.xdark.classfile.type;

public enum PrimitiveKind {
	T_BOOLEAN(4),
	T_CHAR(5),
	T_FLOAT(6),
	T_DOUBLE(7),
	T_BYTE(8),
	T_SHORT(9),
	T_INT(10),
	T_LONG(11),
	T_VOID(12);

	private static final PrimitiveKind[] VALUES = values();
	private final int id;

	PrimitiveKind(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

	public static PrimitiveKind byId(int id) {
		if (id < 4 || id > 11) {
			return null;
		}
		return VALUES[id - 4];
	}
}
