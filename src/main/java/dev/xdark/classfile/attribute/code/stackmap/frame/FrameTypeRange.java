package dev.xdark.classfile.attribute.code.stackmap.frame;

public interface FrameTypeRange {

	int from();

	int to();

	default boolean inRange(int type) {
		return type >= from() && type <= to();
	}
}
