package dev.xdark.classfile.attribute.code.stackmap.type;

import dev.xdark.classfile.attribute.code.stackmap.type.impl.DoubleVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.FloatVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.IntegerVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.LongVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.NullVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.ObjectVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.TopVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.UninitializedThisVariableInfoImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.impl.UninitializedVariableInfoImpl;
import dev.xdark.classfile.io.Codec;

public final class VerificationTypeDescriptor<I extends VerificationTypeInfo> {
	private static final VerificationTypeDescriptor<?>[] DESCRIPTORS = new VerificationTypeDescriptor[9];
	public static final VerificationTypeDescriptor<TopVariableInfo> ITEM_Top = create(0, "ITEM_Top", TopVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<IntegerVariableInfo> ITEM_Integer = create(1, "ITEM_Integer", IntegerVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<FloatVariableInfo> ITEM_Float = create(2, "ITEM_Float", FloatVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<DoubleVariableInfo> ITEM_Double = create(3, "ITEM_Double", DoubleVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<LongVariableInfo> ITEM_Long = create(4, "ITEM_Long", LongVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<NullVariableInfo> ITEM_Null = create(5, "ITEM_Null", NullVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<UninitializedThisVariableInfo> ITEM_UninitializedThis = create(6, "ITEM_UninitializedThis", UninitializedThisVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<ObjectVariableInfo> ITEM_Object = create(7, "ITEM_Object", ObjectVariableInfoImpl.codec());
	public static final VerificationTypeDescriptor<UninitializedVariableInfo> ITEM_Uninitialized = create(8, "ITEM_Uninitialized", UninitializedVariableInfoImpl.codec());

	private final int id;
	private final String mnemonic;
	private final Codec<I> codec;

	private VerificationTypeDescriptor(int id, String mnemonic, Codec<I> codec) {
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

	public Codec<I> codec() {
		return codec;
	}

	public static VerificationTypeDescriptor<?> byId(int id) {
		if (id < 0 || id > 8) {
			return null;
		}
		return DESCRIPTORS[id];
	}

	private static <I extends VerificationTypeInfo> VerificationTypeDescriptor<I> create(int id, String mnemonic, Codec<I> codec) {
		VerificationTypeDescriptor<I> descriptor = new VerificationTypeDescriptor<>(id, mnemonic, codec);
		DESCRIPTORS[id] = descriptor;
		return descriptor;
	}
}
