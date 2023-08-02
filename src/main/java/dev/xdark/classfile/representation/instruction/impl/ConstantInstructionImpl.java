package dev.xdark.classfile.representation.instruction.impl;

import dev.xdark.classfile.representation.bytecode.BytecodeVisitor;
import dev.xdark.classfile.representation.entity.constant.LoadableConstant;
import dev.xdark.classfile.representation.instruction.ConstantDoubleInstruction;
import dev.xdark.classfile.representation.instruction.ConstantDynamicInstruction;
import dev.xdark.classfile.representation.instruction.ConstantFloatInstruction;
import dev.xdark.classfile.representation.instruction.ConstantInstruction;
import dev.xdark.classfile.representation.instruction.ConstantIntInstruction;
import dev.xdark.classfile.representation.instruction.ConstantLongInstruction;
import dev.xdark.classfile.representation.instruction.ConstantMethodHandleInstruction;
import dev.xdark.classfile.representation.instruction.ConstantStringInstruction;
import dev.xdark.classfile.representation.instruction.ConstantTypeInstruction;
import dev.xdark.classfile.type.MethodType;
import dev.xdark.classfile.type.ObjectType;

public abstract class ConstantInstructionImpl<C extends LoadableConstant> implements ConstantInstruction<C> {
	protected final C constant;

	ConstantInstructionImpl(C constant) {
		this.constant = constant;
	}

	@Override
	public final C constant() {
		return constant;
	}

	public static final class StringImpl extends ConstantInstructionImpl<LoadableConstant.OfString> implements ConstantStringInstruction {

		public StringImpl(LoadableConstant.OfString constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushString(constant.value());
		}
	}

	public static final class TypeImpl extends ConstantInstructionImpl<LoadableConstant.OfType> implements ConstantTypeInstruction {

		public TypeImpl(LoadableConstant.OfType constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			dev.xdark.classfile.type.Type type = constant.value();
			if (type instanceof ObjectType) {
				visitor.pushType((ObjectType) type);
			} else {
				visitor.pushType((MethodType) type);
			}
		}
	}

	public static final class DynamicImpl extends ConstantInstructionImpl<LoadableConstant.OfDynamic> implements ConstantDynamicInstruction {

		public DynamicImpl(LoadableConstant.OfDynamic constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushDynamic(constant.value());
		}
	}

	public static final class MethodHandleImpl extends ConstantInstructionImpl<LoadableConstant.OfMethodHandle> implements ConstantMethodHandleInstruction {

		public MethodHandleImpl(LoadableConstant.OfMethodHandle constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushMethodHandle(constant.value());
		}
	}

	public static final class LongImpl extends ConstantInstructionImpl<LoadableConstant.OfLong> implements ConstantLongInstruction {

		public LongImpl(LoadableConstant.OfLong constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushLong(constant.value());
		}
	}

	public static final class DoubleImpl extends ConstantInstructionImpl<LoadableConstant.OfDouble> implements ConstantDoubleInstruction {

		public DoubleImpl(LoadableConstant.OfDouble constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushDouble(constant.value());
		}
	}

	public static final class IntImpl extends ConstantInstructionImpl<LoadableConstant.OfInt> implements ConstantIntInstruction {

		public IntImpl(LoadableConstant.OfInt constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushInt(constant.value());
		}
	}

	public static final class FloatImpl extends ConstantInstructionImpl<LoadableConstant.OfFloat> implements ConstantFloatInstruction {

		public FloatImpl(LoadableConstant.OfFloat constant) {
			super(constant);
		}

		@Override
		public void accept(BytecodeVisitor visitor) {
			visitor.pushFloat(constant.value());
		}
	}

}
