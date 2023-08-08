package dev.xdark.classfile.attribute.code.stackmap.frame;

import dev.xdark.classfile.attribute.code.stackmap.frame.impl.AppendFrameImpl;
import dev.xdark.classfile.attribute.code.stackmap.type.VerificationTypeInfo;

import java.util.List;

public interface AppendFrame extends StackMapFrame {

	List<VerificationTypeInfo> locals();

	@Override
	FrameType<AppendFrame> type();

	static AppendFrame create(int offsetDelta, List<VerificationTypeInfo> locals) {
		int n = locals.size();
		if (n < 1 || n > 3) {
			throw new IllegalArgumentException(String.format("Illegal amount of appended locals %d", n));
		}
		return new AppendFrameImpl(offsetDelta, locals);
	}
}
