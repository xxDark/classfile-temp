package dev.xdark.classfile.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ReferenceKind {
	REF_getField(1),
	REF_getStatic(2),
	REF_putField(3),
	REF_putStatic(4),
	REF_invokeVirtual(5),
	REF_invokeStatic(6),
	REF_invokeSpecial(7),
	REF_newInvokeSpecial(8),
	REF_invokeInterface(9);

	public static final List<ReferenceKind> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private final int id;

	ReferenceKind(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

	public static ReferenceKind byId(int id) {
		if (id < 1 || id > 9) {
			throw new IllegalArgumentException("Illegal bytecode behaviour " + id);
		}
		return VALUES.get(id - 1);
	}
}
