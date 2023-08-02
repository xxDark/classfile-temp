package dev.xdark.classfile.constantpool;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface ConstantPool extends Iterable<Constant> {

	<C extends Constant> C get(int index, Tag<C> tag);

	Constant get(int index);

	int size();

	default Stream<Constant> stream() {
		return StreamSupport.stream(spliterator(), false);
	}
}
