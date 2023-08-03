package dev.xdark.classfile.representation.rw;

import dev.xdark.classfile.attribute.Attributable;
import dev.xdark.classfile.attribute.AttributesVisitor;
import dev.xdark.classfile.attribute.shared.annotation.Element;
import dev.xdark.classfile.attribute.shared.annotation.ElementAnnotation;
import dev.xdark.classfile.attribute.shared.annotation.ElementArray;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeInvisibleAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeVisibleAnnotationsAttribute;
import dev.xdark.classfile.constantpool.ConstantUtf8;
import dev.xdark.classfile.constantpool.MutableConstantPool;
import dev.xdark.classfile.representation.Annotatable;
import dev.xdark.classfile.representation.AnnotationsVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationContainerVisitor;
import dev.xdark.classfile.representation.annotation.AnnotationValue;
import dev.xdark.classfile.representation.annotation.ValueArrayVisitor;
import dev.xdark.classfile.representation.annotation.impl.ValueInternal;
import dev.xdark.classfile.type.InstanceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class AnnotatableWriter implements Annotatable, AutoCloseable {
	private final List<ElementAnnotation> visibleRuntimeAnnotations = new ArrayList<>();
	private final List<ElementAnnotation> invisibleRuntimeAnnotations = new ArrayList<>();
	private final MutableConstantPool constantPool;
	private final Attributable attributable;

	AnnotatableWriter(MutableConstantPool constantPool, Attributable attributable) {
		this.constantPool = constantPool;
		this.attributable = attributable;
	}

	@Override
	public final AnnotationsVisitor visitVisibleRuntimeAnnotations() {
		return visit(visibleRuntimeAnnotations);
	}

	@Override
	public final AnnotationsVisitor visitInvisibleRuntimeAnnotations() {
		return visit(invisibleRuntimeAnnotations);
	}

	@Override
	public void close() {
		addAnnotations(visibleRuntimeAnnotations, RuntimeVisibleAnnotationsAttribute::create);
		addAnnotations(invisibleRuntimeAnnotations, RuntimeInvisibleAnnotationsAttribute::create);
	}

	private void addAnnotations(List<ElementAnnotation> annotations, Function<List<ElementAnnotation>, ? extends RuntimeAnnotationsAttribute> fn) {
		if (!annotations.isEmpty()) {
			AttributesVisitor av = attributable.visitAttributes();
			Objects.requireNonNull(av);
			RuntimeAnnotationsAttribute attr = fn.apply(annotations);
			av.visit(constantPool.add(ConstantUtf8.create(attr.info().name())), attr);
		}
	}

	private AnnotationsVisitor visit(List<ElementAnnotation> annotations) {
		AttributesVisitor av = attributable.visitAttributes();
		if (av == null) {
			return null;
		}
		return type -> {
			MutableConstantPool constantPool = this.constantPool;
			int typeIndex = constantPool.add(ConstantUtf8.create(type.descriptor()));
			return new AnnotationWriter(typeIndex, constantPool, annotations::add);
		};
	}

	private static final class AnnotationWriter implements AnnotationContainerVisitor {
		private final int typeIndex;
		private final MutableConstantPool constantPool;
		private final GrowableIntArray names;
		private final List<Element> elements;
		private final Consumer<ElementAnnotation> sink;

		AnnotationWriter(int typeIndex, MutableConstantPool constantPool, Consumer<ElementAnnotation> sink) {
			this.typeIndex = typeIndex;
			this.constantPool = constantPool;
			this.sink = sink;
			names = new GrowableIntArray();
			elements = new ArrayList<>();
		}

		@Override
		public void visit(String name, AnnotationValue value) {
			MutableConstantPool constantPool = this.constantPool;
			names.add(constantPool.add(ConstantUtf8.create(name)));
			elements.add(((ValueInternal) value).denormalize(constantPool));
		}

		@Override
		public AnnotationContainerVisitor visitAnnotation(String name, InstanceType type) {
			MutableConstantPool constantPool = this.constantPool;
			names.add(constantPool.add(ConstantUtf8.create(name)));
			int typeIndex = constantPool.add(ConstantUtf8.create(type.descriptor()));
			return new AnnotationWriter(typeIndex, constantPool, elements::add);
		}

		@Override
		public ValueArrayVisitor visitArray(String name) {
			names.add(constantPool.add(ConstantUtf8.create(name)));
			return new ArrayWriter(constantPool, elements::add);
		}

		@Override
		public void visitEnd() {
			sink.accept(ElementAnnotation.create(typeIndex, names.toArray(), elements));
		}
	}

	private static final class ArrayWriter implements ValueArrayVisitor {
		private final MutableConstantPool constantPool;
		private final Consumer<ElementArray> sink;
		private final List<Element> elements;

		private ArrayWriter(MutableConstantPool constantPool, Consumer<ElementArray> sink) {
			this.constantPool = constantPool;
			this.sink = sink;
			elements = new ArrayList<>();
		}

		@Override
		public void visit(AnnotationValue value) {
			elements.add(((ValueInternal) value).denormalize(constantPool));
		}

		@Override
		public AnnotationContainerVisitor visitAnnotation(InstanceType type) {
			MutableConstantPool constantPool = this.constantPool;
			int typeIndex = constantPool.add(ConstantUtf8.create(type.descriptor()));
			return new AnnotationWriter(typeIndex, constantPool, elements::add);
		}

		@Override
		public ValueArrayVisitor visitArray() {
			return new ArrayWriter(constantPool, elements::add);
		}

		@Override
		public void visitEnd() {
			sink.accept(ElementArray.create(elements));
		}
	}
}
