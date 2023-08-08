package dev.xdark.classfile.representation.stacmap;

import dev.xdark.classfile.representation.Label;

import java.util.List;

public interface Frame {

	Label location();

	FrameType type();

	List<VerificationTypeInfo> stack();

	List<VerificationTypeInfo> locals();
}
