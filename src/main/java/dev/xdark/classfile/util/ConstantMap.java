package dev.xdark.classfile.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToIntFunction;

public final class ConstantMap<K, V> {

	private final K[] keys;
	private final V[] values;
	private final ToIntFunction<K> hashFunction;
	private int salt;

	public ConstantMap(int size, ToIntFunction<K> hashFunction) {
		keys = (K[]) new Object[size];
		values = (V[]) new Object[size];
		this.hashFunction = hashFunction;
	}

	public void put(K key, V value) {
		K[] keys = this.keys;
		int index = index(key);
		if (keys[index] != null) {
			V[] values = this.values;
			K[] keysCopy = keys.clone();
			V[] newValues = (V[]) new Object[keysCopy.length];
			loop:
			while (true) {
				salt = ThreadLocalRandom.current().nextInt();
				index = index(key);
				Arrays.fill(keys, null);
				Arrays.fill(newValues, null);
				keys[index] = key;
				newValues[index] = value;
				for (int i = 0; i < keysCopy.length; i++) {
					K toInsert = keysCopy[i];
					if (toInsert == null) {
						continue;
					}
					index = index(toInsert);
					if (keys[index] != null) {
						continue loop;
					}
					keys[index] = toInsert;
					newValues[index] = values[i];
				}
				System.arraycopy(newValues, 0, values, 0, newValues.length);
				return;
			}
		}
		keys[index] = key;
		values[index] = value;
	}

	public V get(K key) {
		int index = index(key);
		if (Objects.equals(key, keys[index])) {
			return values[index];
		}
		return null;
	}

	private int index(K key) {
		int hash = hashFunction.applyAsInt(key);
		int salt = this.salt;
		hash ^= salt;
		hash |= hash << (salt & 31);
		return (hash & Integer.MAX_VALUE) % keys.length;
	}
}
