package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.util.ConstantMap;

final class TagTable {
	private static final int TABLE_SIZE = 21;
	private final ConstantMap<String, Tag<?>> tagMap = new ConstantMap<>(TABLE_SIZE, value -> {
		int len = value.length();
		// "CONSTANT_".length() + 1
		int hash = value.charAt(10);
		hash |= value.charAt(len - 1) << 4;
		hash |= hash << 16;
		hash |= value.charAt(len - 3) << 8;
		return hash;
	});
	private final Tag<?>[] tagIds = new Tag[TABLE_SIZE];

	void add(Tag<?> tag) {
		tagMap.put(tag.mnemonic(), tag);
		tagIds[tag.id()] = tag;
	}

	Tag<?> get(String name) {
		if (name.length() < 13) {
			return null;
		}
		return tagMap.get(name);
	}

	Tag<?> get(int id) {
		if (id < 0 || id >= TABLE_SIZE) {
			return null;
		}
		Tag<?> tag = tagIds[id];
		if (tag != null && id != tag.id()) {
			throw new RuntimeException(String.format("Tag id mismatch: %d != %d", tag.id(), id));
		}
		return tag;
	}
}
