package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;

public interface SameLocals1StackItemFrameExtended extends StackMapFrame {

	VerificationTypeInfo item();

	@Override
	FrameType<SameLocals1StackItemFrameExtended> type();
}
