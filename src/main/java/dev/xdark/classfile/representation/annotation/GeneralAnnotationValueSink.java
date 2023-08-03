package dev.xdark.classfile.representation.annotation;

public abstract class GeneralAnnotationValueSink implements AnnotationValueSink {

	protected abstract void accept(AnnotationValue value);

	@Override
	public final void visitContainer(AnnotationContainer container) {
		accept(container);
	}

	@Override
	public final void acceptArray(ValueArray array) {
		accept(array);
	}

	@Override
	public final void acceptEnum(ValueEnum en) {
		accept(en);
	}

	@Override
	public final void acceptString(ValueString string) {
		accept(string);
	}

	@Override
	public final void acceptType(ValueType type) {
		accept(type);
	}

	@Override
	public final void acceptLong(ValueLong value) {
		accept(value);
	}

	@Override
	public final void acceptDouble(ValueDouble value) {
		accept(value);
	}

	@Override
	public final void acceptInt(ValueInt value) {
		accept(value);
	}

	@Override
	public final void acceptFloat(ValueFloat value) {
		accept(value);
	}

	@Override
	public final void acceptChar(ValueChar value) {
		accept(value);
	}

	@Override
	public final void acceptShort(ValueShort value) {
		accept(value);
	}

	@Override
	public final void acceptByte(ValueByte value) {
		accept(value);
	}

	@Override
	public final void acceptBoolean(ValueBoolean value) {
		accept(value);
	}
}
