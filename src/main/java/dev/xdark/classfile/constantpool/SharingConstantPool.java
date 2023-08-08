package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.util.MutableIterator;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public final class SharingConstantPool implements MutableConstantPool {
	private final Map<Constant, Integer> indexMap;
	private final MutableConstantPool constantPool;

	public SharingConstantPool(MutableConstantPool constantPool) {
		this.constantPool = constantPool;
		int index = 1;
		Map<Constant, Integer> indexMap = new HashMap<>();
		for (Constant cst : constantPool) {
			indexMap.putIfAbsent(cst, index);
			if (cst instanceof ConstantWide) {
				index += 2;
			} else {
				index++;
			}
		}
		this.indexMap = indexMap;
	}

	@Override
	public int add(Constant constant) {
		int idx = constantPool.size();
		Integer existing = indexMap.putIfAbsent(constant, idx);
		if (existing == null) {
			int addedIndex = constantPool.add(constant);
			if (idx != addedIndex) {
				throw new IllegalStateException(String.format("Wrong indices: %d != %d", idx, addedIndex));
			}
			return idx;
		}
		return existing;
	}

	@Override
	public Constant set(int index, Constant constant) {
		Constant old = constantPool.set(index, constant);
		indexMap.remove(old, index);
		indexMap.putIfAbsent(constant, index);
		return old;
	}

	@Override
	public <C extends Constant> C get(int index, Tag<C> tag) {
		return constantPool.get(index, tag);
	}

	@Override
	public Constant get(int index) {
		return constantPool.get(index);
	}

	@Override
	public int size() {
		return constantPool.size();
	}

	@Override
	public MutableIterator<Constant> iterator() {
		MutableConstantPool constantPool = this.constantPool;
		return new MutableIterator<Constant>() {
			int idx;

			@Override
			public boolean hasNext() {
				return idx < constantPool.size();
			}

			@Override
			public Constant next() {
				MutableConstantPool pool = constantPool;
				int idx = this.idx;
				if (idx >= pool.size()) {
					throw new NoSuchElementException();
				}
				Constant constant = pool.get(idx);
				this.idx = idx + 1;
				return constant;
			}

			@Override
			public void set(Constant constant) {
				MutableConstantPool pool = constantPool;
				int idx = this.idx;
				if (idx >= pool.size()) {
					throw new NoSuchElementException();
				}
				pool.set(idx, constant);
			}
		};
	}
}
