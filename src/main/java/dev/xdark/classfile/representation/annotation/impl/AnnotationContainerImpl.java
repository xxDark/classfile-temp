package dev.xdark.classfile.representation.annotation.impl;

import dev.xdark.classfile.representation.annotation.AnnotationContainer;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.AnnotationValueSink;
import dev.xdark.classfile.representation.annotation.ValueArray;
import dev.xdark.classfile.representation.annotation.ValueArrayVisitor;
import dev.xdark.classfile.type.InstanceType;

import java.util.Map;

public final class AnnotationContainerImpl implements AnnotationContainer {
	private final InstanceType type;
	private final Map<String, AnnotationValue> values;

	public AnnotationContainerImpl(InstanceType type, Map<String, AnnotationValue> values) {
		this.type = type;
		this.values = values;
	}

	@Override
	public InstanceType type() {
		return type;
	}

	@Override
	public Map<String, AnnotationValue> values() {
		return values;
	}

	@Override
	public void accept(AnnotationContainerVisitor visitor) {
		for (Map.Entry<String, AnnotationValue> entry : values.entrySet()) {
			String name = entry.getKey();
			AnnotationValue value = entry.getValue();
			if (value instanceof AnnotationContainer) {
				AnnotationContainer ac = (AnnotationContainer) value;
				AnnotationContainerVisitor nested = visitor.visitAnnotation(name, ac.type());
				if (nested != null) {
					ac.accept(nested);
				}
			} else if (value instanceof ValueArray) {
				ValueArray array = (ValueArray) value;
				ValueArrayVisitor nested = visitor.visitArray(name);
				if (nested != null) {
					array.accept(nested);
				}
			} else {
				visitor.visit(name, value);
			}
		}
	}

	@Override
	public void accept(AnnotationValueSink sink) {
		sink.visitContainer(this);
	}
}
