package dev.xdark.classfile.representation.model.impl;

import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationContainer;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueArray;
import dev.xdark.classfile.representation.annotation.ValueArrayVisitor;
import dev.xdark.classfile.type.InstanceType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

final class AnnotationsCollector implements AnnotationsVisitor {
	private final List<AnnotationContainer> annotations;

	AnnotationsCollector(List<AnnotationContainer> annotations) {
		this.annotations = annotations;
	}

	@Override
	public AnnotationContainerVisitor visitAnnotation(InstanceType type) {
		AnnotationContainer container = AnnotationContainer.create(type, new LinkedHashMap<>());
		annotations.add(container);
		return new ContainerVisitor(container);
	}

	private static final class ContainerVisitor implements AnnotationContainerVisitor {
		private final AnnotationContainer container;

		ContainerVisitor(AnnotationContainer container) {
			this.container = container;
		}

		@Override
		public void visit(String name, AnnotationValue value) {
			container.values().put(name, value);
		}

		@Override
		public AnnotationContainerVisitor visitAnnotation(String name, InstanceType type) {
			AnnotationContainer container = AnnotationContainer.create(type, new LinkedHashMap<>());
			container.values().put(name, container);
			return new ContainerVisitor(container);
		}

		@Override
		public ValueArrayVisitor visitArray(String name) {
			ValueArray nested = ValueArray.create(new ArrayList<>());
			container.values().put(name, nested);
			return new ArrayVisitor(nested);
		}

		@Override
		public void visitEnd() {
		}
	}

	private static final class ArrayVisitor implements ValueArrayVisitor {
		final ValueArray array;

		ArrayVisitor(ValueArray array) {
			this.array = array;
		}

		@Override
		public void visit(AnnotationValue value) {
			array.values().add(value);
		}

		@Override
		public AnnotationContainerVisitor visitAnnotation(InstanceType type) {
			AnnotationContainer container = AnnotationContainer.create(type, new LinkedHashMap<>());
			array.values().add(container);
			return new ContainerVisitor(container);
		}

		@Override
		public ValueArrayVisitor visitArray() {
			ValueArray nested = ValueArray.create(new ArrayList<>());
			array.values().add(nested);
			return new ArrayVisitor(nested);
		}

		@Override
		public void visitEnd() {
		}
	}
}
