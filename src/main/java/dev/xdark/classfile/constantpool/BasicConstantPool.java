package dev.xdark.classfile.constantpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class BasicConstantPool implements MutableConstantPool {
	private final List<Constant> constants;

	public BasicConstantPool() {
		List<Constant> constants = new ArrayList<>();
		constants.add(null);
		this.constants = constants;
	}

	@Override
	public <C extends Constant> C get(int index, Tag<C> tag) {
		//noinspection unchecked
		C c = (C) constants.get(index);
		if (tag != c.tag()) {
			throw new IllegalArgumentException(String.format("Tag mismatch: %s != %s", tag, c.tag()));
		}
		return c;
	}

	@Override
	public Constant get(int index) {
		return constants.get(index);
	}

	@Override
	public int size() {
		return constants.size();
	}

	@Override
	public int add(Constant constant) {
		List<Constant> constants = this.constants;
		int newIndex = constants.size();
		constants.add(constant);
		if (constant instanceof ConstantWide) {
			constants.add(null);
		}
		return newIndex;
	}

	@Override
	public Constant set(int index, Constant constant) {
		List<Constant> constants = this.constants;
		Constant old = constants.get(index);
		if ((old instanceof ConstantWide) != (constant instanceof ConstantWide)) {
			throw new IllegalStateException("Cannot mix wide constants");
		}
		constants.set(index, constant);
		return old;
	}

	@Override
	public Iterator<Constant> iterator() {
		return stream().filter(Objects::nonNull).iterator();
	}

	@Override
	public Stream<Constant> stream() {
		return constants.stream();
	}
}
