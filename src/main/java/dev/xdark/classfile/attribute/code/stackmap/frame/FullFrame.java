package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.frame.impl.FullFrameImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;

import java.util.List;

public interface FullFrame extends StackMapFrame {

	List<VerificationTypeInfo> locals();

	List<VerificationTypeInfo> stack();

	@Override
	FrameType<FullFrame> type();

	static FullFrame create(int offsetDelta, List<VerificationTypeInfo> locals, List<VerificationTypeInfo> stack) {
		return new FullFrameImpl(offsetDelta, locals, stack);
	}
}
