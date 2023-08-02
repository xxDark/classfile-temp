package dev.xdark.classfile.representation.entity.constant;

import dev.xdark.classfile.representation.entity.constant.impl.OfDoubleImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfDynamicImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfFloatImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfIntImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfLongImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfMethodHandleImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfStringImpl;
import dev.xdark.classfile.representation.entity.constant.impl.OfTypeImpl;
import dev.xdark.classfile.representation.entity.indy.ConstantDynamic;
import dev.xdark.classfile.representation.entity.indy.MethodHandle;
import dev.xdark.classfile.type.Type;

public interface LoadableConstant {

	void accept(ConstantSink sink);

	interface OfString extends LoadableConstant {

		String value();
	}

	interface OfLong extends LoadableConstant {

		long value();
	}

	interface OfDouble extends LoadableConstant {

		double value();
	}

	interface OfInt extends LoadableConstant {

		int value();
	}

	interface OfFloat extends LoadableConstant {

		float value();
	}

	interface OfType extends LoadableConstant {

		Type value();
	}

	interface OfMethodHandle extends LoadableConstant {

		MethodHandle value();
	}

	interface OfDynamic extends LoadableConstant {

		ConstantDynamic value();
	}

	static OfString create(String value) {
		return new OfStringImpl(value);
	}

	static OfLong create(long value) {
		return new OfLongImpl(value);
	}

	static OfDouble create(double value) {
		return new OfDoubleImpl(value);
	}

	static OfInt create(int value) {
		return new OfIntImpl(value);
	}

	static OfFloat create(float value) {
		return new OfFloatImpl(value);
	}

	static OfType create(Type value) {
		return new OfTypeImpl(value);
	}

	static OfMethodHandle create(MethodHandle value) {
		return new OfMethodHandleImpl(value);
	}

	static OfDynamic create(ConstantDynamic value) {
		return new OfDynamicImpl(value);
	}
}
