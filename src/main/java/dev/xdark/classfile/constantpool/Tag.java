package dev.xdark.classfile.constantpool;

import dev.xdark.classfile.constantpool.impl.ConstantClassImpl;
import dev.xdark.classfile.constantpool.impl.ConstantDoubleImpl;
import dev.xdark.classfile.constantpool.impl.ConstantDynamicImpl;
import dev.xdark.classfile.constantpool.impl.ConstantFieldRefImpl;
import dev.xdark.classfile.constantpool.impl.ConstantFloatImpl;
import dev.xdark.classfile.constantpool.impl.ConstantIntImpl;
import dev.xdark.classfile.constantpool.impl.ConstantInterfaceMethodRefImpl;
import dev.xdark.classfile.constantpool.impl.ConstantInvokeDynamicImpl;
import dev.xdark.classfile.constantpool.impl.ConstantLongImpl;
import dev.xdark.classfile.constantpool.impl.ConstantMethodHandleImpl;
import dev.xdark.classfile.constantpool.impl.ConstantMethodRefImpl;
import dev.xdark.classfile.constantpool.impl.ConstantMethodTypeImpl;
import dev.xdark.classfile.constantpool.impl.ConstantModuleImpl;
import dev.xdark.classfile.constantpool.impl.ConstantNameAndTypeImpl;
import dev.xdark.classfile.constantpool.impl.ConstantPackageImpl;
import dev.xdark.classfile.constantpool.impl.ConstantStringImpl;
import dev.xdark.classfile.constantpool.impl.ConstantUtf8Impl;
import dev.xdark.classfile.io.Codec;

public final class Tag<C extends Constant> {
	private static final TagTable TAG_TABLE = new TagTable();
	public static final Tag<ConstantUtf8> Utf8 = create(1, "CONSTANT_Utf8", ConstantUtf8Impl.codec());
	public static final Tag<ConstantInt> Integer = create(3, "CONSTANT_Integer", ConstantIntImpl.codec());
	public static final Tag<ConstantFloat> Float = create(4, "CONSTANT_Float", ConstantFloatImpl.codec());
	public static final Tag<ConstantLong> Long = create(5, "CONSTANT_Long", ConstantLongImpl.codec());
	public static final Tag<ConstantDouble> Double = create(6, "CONSTANT_Double", ConstantDoubleImpl.codec());
	public static final Tag<ConstantClass> Class = create(7, "CONSTANT_Class", ConstantClassImpl.codec());
	public static final Tag<ConstantString> String = create(8, "CONSTANT_String", ConstantStringImpl.codec());
	public static final Tag<ConstantFieldRef> FieldRef = create(9, "CONSTANT_Fieldref", ConstantFieldRefImpl.codec());
	public static final Tag<ConstantMethodRef> MethodRef = create(10, "CONSTANT_Methodref", ConstantMethodRefImpl.CODEC());
	public static final Tag<ConstantInterfaceMethodRef> InterfaceMethodRef = create(11, "CONSTANT_InterfaceMethodref", ConstantInterfaceMethodRefImpl.codec());
	public static final Tag<ConstantNameAndType> NameAndType = create(12, "CONSTANT_NameAndType", ConstantNameAndTypeImpl.codec());
	public static final Tag<ConstantMethodHandle> MethodHandle = create(15, "CONSTANT_MethodHandle", ConstantMethodHandleImpl.codec());
	public static final Tag<ConstantMethodType> MethodType = create(16, "CONSTANT_MethodType", ConstantMethodTypeImpl.codec());
	public static final Tag<ConstantDynamic> ConstantDynamic = create(17, "CONSTANT_Dynamic", ConstantDynamicImpl.codec());
	public static final Tag<ConstantInvokeDynamic> InvokeDynamic = create(18, "CONSTANT_InvokeDynamic", ConstantInvokeDynamicImpl.codec());
	public static final Tag<ConstantModule> Module = create(19, "CONSTANT_Module", ConstantModuleImpl.codec());
	public static final Tag<ConstantPackage> Package = create(20, "CONSTANT_Package", ConstantPackageImpl.codec());

	private final int id;
	private final String mnemonic;
	private final Codec<C> codec;

	private Tag(int id, String mnemonic, Codec<C> codec) {
		this.id = id;
		this.mnemonic = mnemonic;
		this.codec = codec;
	}

	public int id() {
		return id;
	}

	public String mnemonic() {
		return mnemonic;
	}

	public Codec<C> codec() {
		return codec;
	}

	public static Tag<?> byMnemonic(String mnemonic) {
		return TAG_TABLE.get(mnemonic);
	}

	public static Tag<?> byId(int id) {
		return TAG_TABLE.get(id);
	}

	@Override
	public String toString() {
		return mnemonic;
	}

	private static <C extends Constant> Tag<C> create(int id, String mnemonic, Codec<C> codec) {
		Tag<C> tag = new Tag<>(id, mnemonic, codec);
		TAG_TABLE.add(tag);
		return tag;
	}
}
