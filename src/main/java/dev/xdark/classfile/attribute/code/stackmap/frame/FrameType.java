package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.frame.impl.AppendFrameImpl;
import dev.xdark.classfile.attribute.code.stackmap.frame.impl.ChopFrameImpl;
import dev.xdark.classfile.attribute.code.stackmap.frame.impl.FullFrameImpl;
import dev.xdark.classfile.attribute.code.stackmap.frame.impl.SameFrameExtendedImpl;
import dev.xdark.classfile.attribute.code.stackmap.frame.impl.SameFrameImpl;
import dev.xdark.classfile.attribute.code.stackmap.frame.impl.SameLocals1StackItemFrameExtendedImpl;
import dev.xdark.classfile.attribute.code.stackmap.frame.impl.SameLocals1StackItemFrameImpl;
import dev.xdark.classfile.io.Codec;
import dev.xdark.classfile.util.ConstantMap;

public final class FrameType<F extends StackMapFrame> {
	private static final FrameType<?>[] TYPES = new FrameType[256];
	private static final ConstantMap<String, FrameType<?>> MAP = new ConstantMap<>(9, value -> {
		return value.length() << 1 | value.charAt(0);
	});
	public static final FrameType<SameFrame> SAME = range("SAME", 0, 63, SameFrameImpl.codec());
	public static final FrameType<SameLocals1StackItemFrame> SAME_LOCALS_1_STACK_ITEM = range("SAME_LOCALS_1_STACK_ITEM", 64, 127, SameLocals1StackItemFrameImpl.codec());
	public static final FrameType<SameLocals1StackItemFrameExtended> SAME_LOCALS_1_STACK_ITEM_EXTENDED = exact("SAME_LOCALS_1_STACK_ITEM_EXTENDED", 247, SameLocals1StackItemFrameExtendedImpl.codec());
	public static final FrameType<ChopFrame> CHOP = range("CHOP", 248, 250, ChopFrameImpl.codec());
	public static final FrameType<SameFrameExtended> SAME_FRAME_EXTENDED = exact("SAME_FRAME_EXTENDED", 251, SameFrameExtendedImpl.codec());
	public static final FrameType<AppendFrame> APPEND = range("APPEND", 252, 254, AppendFrameImpl.codec());
	public static final FrameType<FullFrame> FULL_FRAME = exact("FULL_FRAME", 255, FullFrameImpl.codec());
	private final String mnemonic;
	private final FrameTypeRange range;
	private final Codec<F> codec;

	private FrameType(String mnemonic, FrameTypeRange range, Codec<F> codec) {
		this.mnemonic = mnemonic;
		this.range = range;
		this.codec = codec;
	}

	public String mnemonic() {
		return mnemonic;
	}

	public FrameTypeRange range() {
		return range;
	}

	public Codec<F> codec() {
		return codec;
	}

	public static FrameType<?> byType(int type) {
		if (type < 0 || type > 255) {
			return null;
		}
		return TYPES[type];
	}

	public static FrameType<?> byMnemonic(String mnemonic) {
		if (mnemonic.isEmpty()) return null;
		return MAP.get(mnemonic);
	}

	private static <F extends StackMapFrame> FrameType<F> create(String mnemonic, FrameTypeRange range, Codec<F> codec) {
		FrameType<F> type = new FrameType<>(mnemonic, range, codec);
		FrameType<?>[] types = TYPES;
		for (int i = range.from(), j = range.to(); i <= j; i++) {
			types[i] = type;
		}
		MAP.put(mnemonic, type);
		return type;
	}

	private static <F extends StackMapFrame> FrameType<F> exact(String mnemonic, int type, Codec<F> codec) {
		return create(mnemonic, new ExactFrameTypeRange(type), codec);
	}

	private static <F extends StackMapFrame> FrameType<F> range(String mnemonic, int from, int to, Codec<F> codec) {
		return create(mnemonic, new VaryFrameTypeRange(from, to), codec);
	}
}
