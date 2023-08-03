package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationContainer;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueArray;
import dev.xdark.classfile.representation.annotation.ValueArrayVisitor;
import dev.xdark.classfile.representation.annotation.ValueEnum;

import java.util.List;

public final class ValueArrayImpl implements ValueArray {
	private final List<AnnotationValue> values;

	public ValueArrayImpl(List<AnnotationValue> values) {
		this.values = values;
	}

	@Override
	public List<AnnotationValue> values() {
		return values;
	}

	@Override
	public void accept(ValueArrayVisitor visitor) {
		for (AnnotationValue value : values) {
			if (value instanceof AnnotationContainer) {
				AnnotationContainer ac = (AnnotationContainer) value;
				AnnotationContainerVisitor nested = visitor.visitAnnotation(ac.type());
				if (nested != null) {
					ac.accept(nested);
				}
			} else if (value instanceof ValueArray) {
				ValueArray array = (ValueArray) value;
				ValueArrayVisitor nested = visitor.visitArray();
				if (nested != null) {
					array.accept(nested);
				}
			} else {
				visitor.visit(value);
			}
		}
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.acceptArray(this);
	}
}
