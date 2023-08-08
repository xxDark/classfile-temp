package dev.xdark.classfile.attribute;

import dev.xdark.classfile.ClassFileVersion;
import dev.xdark.classfile.attribute.code.CodeAttribute;
import dev.xdark.classfile.attribute.code.LineNumberTableAttribute;
import dev.xdark.classfile.attribute.code.impl.CodeAttributeImpl;
import dev.xdark.classfile.attribute.code.impl.LineNumberTableAttributeImpl;
import dev.xdark.classfile.attribute.code.stackmap.StackMapTableAttribute;
import dev.xdark.classfile.attribute.code.stackmap.inpl.StackMapTableAttributeImpl;
import dev.xdark.classfile.attribute.field.ConstantValueAttribute;
import dev.xdark.classfile.attribute.field.impl.ConstantValueAttributeImpl;
import dev.xdark.classfile.attribute.klass.BootstrapMethodsAttribute;
import dev.xdark.classfile.attribute.klass.EnclosingMethodAttribute;
import dev.xdark.classfile.attribute.klass.InnerClassesAttribute;
import dev.xdark.classfile.attribute.klass.NestHostAttribute;
import dev.xdark.classfile.attribute.klass.NestMembersAttribute;
import dev.xdark.classfile.attribute.klass.PermittedSubclassesAttribute;
import dev.xdark.classfile.attribute.klass.RecordAttribute;
import dev.xdark.classfile.attribute.klass.SourceDebugExtensionAttribute;
import dev.xdark.classfile.attribute.klass.SourceFileAttribute;
import dev.xdark.classfile.attribute.klass.impl.BootstrapMethodsAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.EnclosingMethodAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.InnerClassesAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.NestHostAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.NestMembersAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.PermittedSubclassesAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.RecordAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.SourceDebugExtensionAttributeImpl;
import dev.xdark.classfile.attribute.klass.impl.SourceFileAttributeImpl;
import dev.xdark.classfile.attribute.klass.module.ModuleAttribute;
import dev.xdark.classfile.attribute.klass.module.ModuleMainClassAttribute;
import dev.xdark.classfile.attribute.klass.module.ModulePackagesAttribute;
import dev.xdark.classfile.attribute.klass.module.impl.ModuleAttributeImpl;
import dev.xdark.classfile.attribute.klass.module.impl.ModuleMainClassAttributeImpl;
import dev.xdark.classfile.attribute.klass.module.impl.ModulePackagesAttributeImpl;
import dev.xdark.classfile.attribute.method.AnnotationDefaultAttribute;
import dev.xdark.classfile.attribute.method.ExceptionsAttribute;
import dev.xdark.classfile.attribute.method.MethodParametersAttribute;
import dev.xdark.classfile.attribute.method.impl.AnnotationDefaultAttributeImpl;
import dev.xdark.classfile.attribute.method.impl.ExceptionsAttributeImpl;
import dev.xdark.classfile.attribute.method.impl.MethodParametersAttributeImpl;
import dev.xdark.classfile.attribute.shared.DeprecatedAttribute;
import dev.xdark.classfile.attribute.shared.SignatureAttribute;
import dev.xdark.classfile.attribute.shared.SyntheticAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeInvisibleAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.RuntimeVisibleAnnotationsAttribute;
import dev.xdark.classfile.attribute.shared.annotation.impl.RuntimeInvisibleAnnotationsAttributeImpl;
import dev.xdark.classfile.attribute.shared.annotation.impl.RuntimeVisibleAnnotationsAttributeImpl;
import dev.xdark.classfile.attribute.shared.impl.DeprecatedAttributeImpl;
import dev.xdark.classfile.attribute.shared.impl.SignatureAttributeImpl;
import dev.xdark.classfile.attribute.shared.impl.SyntheticAttributeImpl;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.util.ConstantMap;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public final class AttributeInfo<A extends SpecAttribute> implements AttributeMapperResult {
	private static final ConstantMap<String, AttributeInfo<?>> NAME_MAP = new ConstantMap<>(80, String::hashCode);

	public static final AttributeInfo<SourceFileAttribute> SourceFile = klass("SourceFile", ClassFileVersion.V1_0, SourceFileAttributeImpl.codec());
	public static final AttributeInfo<InnerClassesAttribute> InnerClasses = klass("InnerClasses", ClassFileVersion.V1_1, InnerClassesAttributeImpl.codec());
	public static final AttributeInfo<EnclosingMethodAttribute> EnclosingMethod = klass("EnclosingMethod", ClassFileVersion.V1_5, EnclosingMethodAttributeImpl.codec());
	public static final AttributeInfo<SourceDebugExtensionAttribute> SourceDebugExtension = klass("SourceDebugExtension", ClassFileVersion.V1_5, SourceDebugExtensionAttributeImpl.codec());
	public static final AttributeInfo<BootstrapMethodsAttribute> BootstrapMethods = klass("BootstrapMethods", ClassFileVersion.V7, BootstrapMethodsAttributeImpl.codec());
	public static final AttributeInfo<ModuleAttribute> Module = klass("Module", ClassFileVersion.V9, ModuleAttributeImpl.codec());
	public static final AttributeInfo<ModulePackagesAttribute> ModulePackages = klass("ModulePackages", ClassFileVersion.V9, ModulePackagesAttributeImpl.codec());
	public static final AttributeInfo<ModuleMainClassAttribute> ModuleMainClass = klass("ModuleMainClass", ClassFileVersion.V9, ModuleMainClassAttributeImpl.codec());
	public static final AttributeInfo<NestHostAttribute> NestHost = klass("NestHost", ClassFileVersion.V11, NestHostAttributeImpl.codec());
	public static final AttributeInfo<NestMembersAttribute> NestMembers = klass("NestMembers", ClassFileVersion.V11, NestMembersAttributeImpl.codec());
	public static final AttributeInfo<RecordAttribute> Record = klass("Record", ClassFileVersion.V16, RecordAttributeImpl.codec());
	public static final AttributeInfo<PermittedSubclassesAttribute> PermittedSubclasses = klass("PermittedSubclasses", ClassFileVersion.V17, PermittedSubclassesAttributeImpl.codec());

	public static final AttributeInfo<SignatureAttribute> Signature = cmfr("Signature", ClassFileVersion.V1_5, SignatureAttributeImpl.codec());
	public static final AttributeInfo<RuntimeVisibleAnnotationsAttribute> RuntimeVisibleAnnotations = cmfr("RuntimeVisibleAnnotations", ClassFileVersion.V1_5, RuntimeVisibleAnnotationsAttributeImpl.codec());
	public static final AttributeInfo<RuntimeInvisibleAnnotationsAttribute> RuntimeInvisibleAnnotations = cmfr("RuntimeInvisibleAnnotations", ClassFileVersion.V1_5, RuntimeInvisibleAnnotationsAttributeImpl.codec());
	public static final AttributeInfo<SyntheticAttribute> Synthetic = cmf("Synthetic", ClassFileVersion.V1_1, SyntheticAttributeImpl.codec());
	public static final AttributeInfo<DeprecatedAttribute> Deprecated = cmf("Deprecated", ClassFileVersion.V1_1, DeprecatedAttributeImpl.codec());

	public static final AttributeInfo<ConstantValueAttribute> ConstantValue = field("ConstantValue", ClassFileVersion.V1_0, ConstantValueAttributeImpl.codec());
	public static final AttributeInfo<CodeAttribute> Code = method("Code", ClassFileVersion.V1_0, CodeAttributeImpl.codec());
	public static final AttributeInfo<ExceptionsAttribute> Exceptions = method("Exceptions", ClassFileVersion.V1_0, ExceptionsAttributeImpl.codec());
	public static final AttributeInfo<MethodParametersAttribute> MethodParameters = method("MethodParameters", ClassFileVersion.V8, MethodParametersAttributeImpl.codec());
	public static final AttributeInfo<AnnotationDefaultAttribute> AnnotationDefault = method("AnnotationDefault", ClassFileVersion.V1_5, AnnotationDefaultAttributeImpl.codec());
	public static final AttributeInfo<LineNumberTableAttribute> LineNumberTable = code("LineNumberTable", ClassFileVersion.V1_0, LineNumberTableAttributeImpl.codec());
	public static final AttributeInfo<StackMapTableAttribute> StackMapTable = code("StackMapTable", ClassFileVersion.V6, StackMapTableAttributeImpl.codec());

	private final String name;
	private final ClassFileVersion introducedVersion;
	private final Set<AttributeLocation> locations;
	private final Codec<A> codec;

	private AttributeInfo(String name, ClassFileVersion introducedVersion, Set<AttributeLocation> locations, Codec<A> codec) {
		this.name = name;
		this.introducedVersion = introducedVersion;
		this.locations = Collections.unmodifiableSet(locations);
		this.codec = codec;
	}

	public String name() {
		return name;
	}

	public ClassFileVersion introducedVersion() {
		return introducedVersion;
	}

	public Set<AttributeLocation> locations() {
		return locations;
	}

	public Codec<A> codec() {
		return codec;
	}

	public static AttributeInfo<?> byName(String name) {
		return NAME_MAP.get(name);
	}

	private static <A extends SpecAttribute> AttributeInfo<A> create(String name, ClassFileVersion introducedVersion, Set<AttributeLocation> locations, Codec<A> codec) {
		AttributeInfo<A> info = new AttributeInfo<>(name, introducedVersion, locations, codec);
		NAME_MAP.put(name, info);
		return info;
	}

	private static <A extends SpecAttribute> AttributeInfo<A> klass(String name, ClassFileVersion introducedVersion, Codec<A> codec) {
		return create(name, introducedVersion, EnumSet.of(AttributeLocation.CLASS), codec);
	}

	private static <A extends SpecAttribute> AttributeInfo<A> field(String name, ClassFileVersion introducedVersion, Codec<A> codec) {
		return create(name, introducedVersion, EnumSet.of(AttributeLocation.FIELD), codec);
	}

	private static <A extends SpecAttribute> AttributeInfo<A> method(String name, ClassFileVersion introducedVersion, Codec<A> codec) {
		return create(name, introducedVersion, EnumSet.of(AttributeLocation.METHOD), codec);
	}

	private static <A extends SpecAttribute> AttributeInfo<A> code(String name, ClassFileVersion introducedVersion, Codec<A> codec) {
		return create(name, introducedVersion, EnumSet.of(AttributeLocation.CODE_ATTRIBUTE), codec);
	}

	private static <A extends SpecAttribute> AttributeInfo<A> cmfr(String name, ClassFileVersion introducedVersion, Codec<A> codec) {
		return create(name, introducedVersion, EnumSet.of(AttributeLocation.CLASS, AttributeLocation.METHOD, AttributeLocation.FIELD, AttributeLocation.RECORD_COMPONENT), codec);
	}

	private static <A extends SpecAttribute> AttributeInfo<A> cmf(String name, ClassFileVersion introducedVersion, Codec<A> codec) {
		return create(name, introducedVersion, EnumSet.of(AttributeLocation.CLASS, AttributeLocation.METHOD, AttributeLocation.FIELD), codec);
	}
}
