package dev.xdark.classfile.type;

public final class Types {

	private Types() {
	}

	public static String internalName(String externalName) {
		return externalName.replace('.', '/');
	}

	public static String externalName(String internalName) {
		return internalName.replace('/', '.');
	}
}
