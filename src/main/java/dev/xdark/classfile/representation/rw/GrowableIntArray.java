package dev.xdark.classfile.representation.rw;

import java.util.Arrays;

final class GrowableIntArray {
	private static final int[] EMPTY = {};
	private int[] array = EMPTY;
	private int index;

	public void add(int value) {
		int index = this.index;
		int[] array = this.array;
		if (index == array.length) {
			int newIdx = index + 4;
			if (newIdx < 0) {
				throw new OutOfMemoryError("Array too large");
			}
			array = Arrays.copyOf(array, newIdx);
			this.array = array;
		}
		array[index] = value;
		this.index = index + 1;
	}

	public int get(int idx) {
		if (idx < 0 || idx >= index) {
			throw new ArrayIndexOutOfBoundsException(Integer.toString(idx));
		}
		return array[idx];
	}

	public int length() {
		return index;
	}

	public int[] toArray() {
		return Arrays.copyOf(array, index);
	}
}
