package dev.xdark.classfile.representation.annotation;

public interface AnnotationValueSink {

	void visitContainer(AnnotationContainer container);

	void acceptArray(ValueArray array);

	void acceptEnum(ValueEnum en);

	void acceptString(ValueString string);

	void acceptType(ValueType type);

	void acceptLong(ValueLong value);

	void acceptDouble(ValueDouble value);

	void acceptInt(ValueInt value);

	void acceptFloat(ValueFloat value);

	void acceptChar(ValueChar value);

	void acceptShort(ValueShort value);

	void acceptByte(ValueByte value);

	void acceptBoolean(ValueBoolean value);
}
